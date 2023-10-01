package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "urlLinks")
public class UrlLink {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;

    /**
     * имя ссылки оригинальное(отправленное в сервис)
     */
    @NotBlank(message = "url link must be not empty")
    private String nameOri;

    /**
     * имя ссылки преобразованное(полученное в виде кода от сервиса)
     */
    @NotBlank(message = "Code must be not empty")
    private String nameMod;

}
