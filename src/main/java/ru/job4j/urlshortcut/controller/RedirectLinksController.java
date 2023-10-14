package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.UrlLink;
import ru.job4j.urlshortcut.service.UrlLinkService;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/redirect")
@AllArgsConstructor
public class RedirectLinksController {

    private final UrlLinkService urlLinkService;

    @GetMapping("/{nameMod}")
    public ResponseEntity<String> redirectLink(@PathVariable String nameMod) {
        ResponseEntity<String> entity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("URL link doesn't exist");
        HashMap<String, String> body = new HashMap<>();
        Optional<UrlLink> urlLink = urlLinkService.findUrlByNameMod(nameMod);

        if (urlLink.isPresent()) {
            body.put("URL", urlLink.get().getNameOri());
            entity = ResponseEntity.status(HttpStatus.FOUND)
                    .header("HTTP CODE", "302 REDIRECT URL")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(body.toString().length())
                    .body(body.toString());
        }
        return entity;
    }
}
