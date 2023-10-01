package ru.job4j.urlshortcut.service;

import org.springframework.http.HttpHeaders;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;

import java.util.List;

public interface UrlLinkService {

    ModifiedLinkDto save(OriginalLinkDto originalLinkDto);

    List<UrlLink> findAll();
}
