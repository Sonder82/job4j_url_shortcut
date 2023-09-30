package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.dto.SignUpSiteDto;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {

    SignUpSiteDto save(SiteDto siteDto);

    Optional<Site> findById(int id);

    List<Site> findAll();

    Optional<Site> findBySiteURL(String siteUrl);

    Optional<Site> findByLogin(String login);
}
