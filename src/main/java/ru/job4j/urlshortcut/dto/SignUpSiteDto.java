package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Класс содержит модель SignUpSite.
 * SignUpSite - хранит данные для входа для сайта (логин и пароль), полученные от сервиса.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpSiteDto {

    /**
     * статус регистрации
     */
    private boolean registration;

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
