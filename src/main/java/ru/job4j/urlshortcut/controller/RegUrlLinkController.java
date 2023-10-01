package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.ModifiedLinkDto;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.model.UrlLink;
import ru.job4j.urlshortcut.service.UrlLinkService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class RegUrlLinkController {

    private final UrlLinkService urlLinkService;

    @GetMapping("/all")
    public List<UrlLink> findAll() {
        return urlLinkService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ModifiedLinkDto> registration(@Valid @RequestBody OriginalLinkDto originalLinkDto) {
        return new ResponseEntity<>(
                urlLinkService.save(originalLinkDto),
                HttpStatus.CREATED
        );
    }
}
