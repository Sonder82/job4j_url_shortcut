package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Класс содержит модель SignUpSite.
 * SignUpSite - хранит данные для входа для сайта (логин и пароль), полученные от сервиса.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "signUpSites")
public class SignUpSite {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;

    /**
     * Логин
     */
    @NotBlank(message = "Login must be not empty")
    private String login;

    /**
     * Пароль
     */
    @Min(value = 6, message = "Password should not be less than 6")
    private String password;
}
