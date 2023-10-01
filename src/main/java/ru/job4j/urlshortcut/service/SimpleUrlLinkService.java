package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;
import ru.job4j.urlshortcut.repository.UrlLinkRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleUrlLinkService implements UrlLinkService {

    UrlLinkRepository urlLinkRepository;

    @Override
    public ModifiedLinkDto save(OriginalLinkDto originalLinkDto) {

        return null;
    }

    @Override
    public List<UrlLink> findAll() {
        return urlLinkRepository.findAll();
    }
}
