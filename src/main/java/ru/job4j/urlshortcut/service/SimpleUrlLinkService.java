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

import java.util.List;

/**
 * Сервис выполняет преобразование полученных url ссылок.
 * Пользователь отправляет на сайт ссылку, получает код
 */
@Service
@AllArgsConstructor
public class SimpleUrlLinkService implements UrlLinkService {

    private final UrlLinkRepository urlLinkRepository;

    private final SiteRepository siteRepository;

    @Override
    public ModifiedLinkDto save(OriginalLinkDto originalLinkDto, Authentication authentication) {
        checkUrlSite(authentication);
        UrlLink url = new UrlLink();
        url.setNameOri(originalLinkDto.getNameOri());
        String modifiedUrl = CodeGenerator.getCode();
        url.setNameMod(modifiedUrl);
        urlLinkRepository.save(url);
        return new ModifiedLinkDto(modifiedUrl);
    }

    private void checkUrlSite(Authentication authentication) {
        String login = authentication.getName();
        if (siteRepository.findByLogin(login).isEmpty()) {
            throw new IllegalArgumentException(
                    "Site doesn't exist in repository, try to register site in service");
        }
    }

    @Override
    public List<UrlLink> findAll() {
        return urlLinkRepository.findAll();
    }
}
