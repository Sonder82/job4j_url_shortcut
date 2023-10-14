package ru.job4j.urlshortcut.repository;


import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlLink;

import java.util.List;
import java.util.Optional;

public interface UrlLinkRepository extends CrudRepository<UrlLink, Integer> {

    List<UrlLink> findAll();

    Optional<UrlLink> findUrlByNameMod(String nameMod);
}
