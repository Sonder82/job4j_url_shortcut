package ru.job4j.urlshortcut.repository;


import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlLink;

import java.util.List;

public interface UrlLinkRepository extends CrudRepository<UrlLink, Integer> {

    List<UrlLink> findAll();

}
