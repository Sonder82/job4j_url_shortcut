package ru.job4j.urlshortcut.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;

import java.util.List;
import java.util.Optional;

public interface UrlLinkService {

    ModifiedLinkDto save(OriginalLinkDto originalLinkDto, Authentication authentication);

    List<UrlLink> findAll();

    Optional<UrlLink> findUrlByNameMod(String nameMod);
}
