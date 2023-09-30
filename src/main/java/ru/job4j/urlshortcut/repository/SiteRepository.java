package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

/**
 *  Интерфейс репозитория для сайтов
 */
public interface SiteRepository extends CrudRepository<Site, Integer> {

    List<Site> findAll();

    Optional<Site> findById(int id);

    Optional<Site> findBySiteURL(String siteUrl);

    Optional<Site> findByLogin(String login);
}
