package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.dto.SignUpSiteDto;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;

import java.util.List;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegSiteController {

    private final SiteService siteService;

    @GetMapping("/all")
    public List<Site> findAll() {
        return siteService.findAll();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpSiteDto> registration(@RequestBody SiteDto siteDto) {
        return new ResponseEntity<>(
                siteService.save(siteDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> findById(@PathVariable int id) {
        var site = siteService.findById(id);
        return new ResponseEntity<>(
                site.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Site is not found")), HttpStatus.OK
        );
    }
}
