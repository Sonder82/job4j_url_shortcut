package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.service.UrlLinkService;

import java.util.HashMap;


@RestController
@RequestMapping("/redirect")
@AllArgsConstructor
public class RedirectLinksController {

    private final UrlLinkService urlLinkService;

    @GetMapping("/{nameMod}")
    public ResponseEntity<String> redirectLink(@PathVariable String nameMod) {
        HashMap<String, String> body = new HashMap<>();
        var urlLink = urlLinkService.getOriModAndIncrement(nameMod);
        body.put("URL", urlLink.getNameOri());

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.toString().length())
                .body(body.toString());
    }
}
