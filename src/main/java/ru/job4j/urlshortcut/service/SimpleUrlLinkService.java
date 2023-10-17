package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.UrlLinkRepository;
import ru.job4j.urlshortcut.utils.CodeGenerator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис выполняет преобразование полученных url ссылок.
 * Пользователь отправляет на сайт ссылку, получает код
 */
@Service
@AllArgsConstructor
public class SimpleUrlLinkService implements UrlLinkService {

    private final UrlLinkRepository urlLinkRepository;

    private final SiteRepository siteRepository;

    /**
     * Метод получает в аргументах URL ссылку.
     * Данная ссылка сохраняется и для нее генерируется преобразованная ссылка(код)
     * @param originalLinkDto URL ссылка
     * @param authentication Аутентификация
     * @return преобразованная ссылка(код)
     */
    @Override
    public ModifiedLinkDto save(OriginalLinkDto originalLinkDto, Authentication authentication) {
        String login = authentication.getName();
        if (siteRepository.findByLogin(login).isEmpty()) {
            throw new IllegalArgumentException(
                    "Site doesn't exist in repository, try to register site in service");
        }

        UrlLink url = new UrlLink();
        url.setSite(siteRepository.findByLogin(login).get());
        url.setNameOri(originalLinkDto.getNameOri());
        String modifiedUrl = CodeGenerator.getCode();
        url.setNameMod(modifiedUrl);
        urlLinkRepository.save(url);
        return new ModifiedLinkDto(modifiedUrl);
    }

    /**
     * Метод принимает в аргументах преобразованную ссылку(код).
     * По этой ссылке находим Optional URL
     * @param nameMod преобразованная ссылка(код)
     * @return Optional URL
     */
    @Override
    public Optional<UrlLink> findByModName(String nameMod) {
        return urlLinkRepository.findUrlByNameMod(nameMod);
    }

    /**
     * Метод принимает в аргументах преобразованную ссылку(код).
     * Получаем объект URL и выполняем инкремент счетчика вызова URL
     * @param nameMod преобразованная ссылка(код)
     * @return URL
     */
    @Transactional
    @Override
    public UrlLink getOriModAndIncrement(String nameMod) {
        Optional<UrlLink> url = findByModName(nameMod);
        if (url.isEmpty()) {
            throw new NoSuchElementException(nameMod + " url doesn't exist");
        }
        incrementCalls(url.get().getId());
        return url.get();
    }

    /**
     * инкремент счетчика вызова URL
     * @param urlId Id URL
     */
    @Override
    public void incrementCalls(int urlId) {
        urlLinkRepository.incrementCalls(urlId);
    }

    /**
     * Получаем из URL репозитория лист с OriginalLinkDto.
     * @return
     */
    @Override
    public List<OriginalLinkDto> findAndGetUrls() {
        return urlLinkRepository.findAll()
                .stream()
                .map(urlLink -> new OriginalLinkDto(urlLink.getNameOri(), urlLink.getCalls()))
                .collect(Collectors.toList());
    }
}


