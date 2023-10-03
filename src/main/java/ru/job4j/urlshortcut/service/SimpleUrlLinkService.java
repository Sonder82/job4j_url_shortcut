package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;
import ru.job4j.urlshortcut.repository.UrlLinkRepository;
import ru.job4j.urlshortcut.utils.CodeGenerator;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleUrlLinkService implements UrlLinkService {

    UrlLinkRepository urlLinkRepository;

    @Override
    public ModifiedLinkDto save(OriginalLinkDto originalLinkDto) {
        UrlLink url = new UrlLink();
        url.setNameOri(originalLinkDto.getNameOri());
        String modifiedUrl = CodeGenerator.getCode();
        url.setNameMod(modifiedUrl);
        urlLinkRepository.save(url);
        return new ModifiedLinkDto(modifiedUrl);
    }

    @Override
    public List<UrlLink> findAll() {
        return urlLinkRepository.findAll();
    }
}
