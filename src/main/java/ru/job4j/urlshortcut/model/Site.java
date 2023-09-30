package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Класс содержит модель Site.
 * Site - это сайт, который будет зарегистрирован в наш сервис.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "sites")
public class Site {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;

    /**
     * URL сайта
     */
    @NotBlank(message = "Web site url must be not empty")
    private String siteURL;

    /**
     * Логин
     */
    @NotBlank(message = "Login must be not empty")
    private String login;

    /**
     * Пароль
     */
    @NotBlank(message = "Password must be not empty")
    private String password;

}
