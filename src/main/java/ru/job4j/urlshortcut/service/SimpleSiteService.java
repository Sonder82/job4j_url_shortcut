package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.SignUpSiteDto;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.utils.CodeGenerator;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Site Service.
 * Сервис для регистрируемых сайтов
 */
@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService, UserDetailsService {

    private final SiteRepository siteRepository;

    private BCryptPasswordEncoder encoder;

    /**
     * Метод выполняет сохранение(регистрацию) сайта в системе.
     * @param siteDto DTO SiteDto,откуда берем URL сайта
     * @return DTO SignUpSiteDto
     */
    @Override
    public SignUpSiteDto save(SiteDto siteDto) {
        checkUrlSite(siteDto);
        Site site = new Site();
        site.setSiteURL(siteDto.getSiteURL());
        site.setLogin(CodeGenerator.getCode());
        site.setPassword(encoder.encode(CodeGenerator.getCode()));
        siteRepository.save(site);
        return new SignUpSiteDto(true, site.getLogin(), site.getPassword());
    }

    /**
     * В методе выполняется проверка на наличие сайта в сервисе
     * @param siteDto URL сайта
     */
    private void checkUrlSite(SiteDto siteDto) {
        if (findBySiteURL(siteDto.getSiteURL()).isPresent()) {
            throw new IllegalArgumentException(String.format("%s site already in use", siteDto.getSiteURL()));
        }
    }

    @Override
    public Optional<Site> findById(int id) {
        return siteRepository.findById(id);
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public Optional<Site> findBySiteURL(String siteUrl) {
        return siteRepository.findBySiteURL(siteUrl);
    }

    @Override
    public Optional<Site> findByLogin(@NonNull String login) {
        return siteRepository.findByLogin(login);
    }

    /**
     * Детали загрузки сайта через авторизацию через логин
     * @param login логин
     * @return UserDetails
     * @throws UsernameNotFoundException Exception
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var site = findByLogin(login);
        if (site.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.get().getLogin(), site.get().getPassword(), emptyList());
    }
}
