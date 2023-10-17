package ru.job4j.urlshortcut.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.UrlLink;

import java.util.List;
import java.util.Optional;

public interface UrlLinkRepository extends CrudRepository<UrlLink, Integer> {

    List<UrlLink> findAll();

    Optional<UrlLink> findUrlByNameMod(String nameMod);

    @Modifying
    @Query(value = """
            UPDATE url_links
            SET calls = calls + 1
            WHERE id = ?1
            """, nativeQuery = true)
    int incrementCalls(int urlId);
}
