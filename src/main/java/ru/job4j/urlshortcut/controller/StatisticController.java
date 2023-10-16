package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.OriginalLinkDto;
import ru.job4j.urlshortcut.service.UrlLinkService;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@AllArgsConstructor
public class StatisticController {

    private final UrlLinkService urlLinkService;

    @GetMapping("/")
    public ResponseEntity<List<OriginalLinkDto>> getStatisticOfUrl() {
        return new ResponseEntity<>(
                urlLinkService.findAndGetUrls(),
                HttpStatus.OK
        );
    }
}
