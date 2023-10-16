package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DTO для передачи ссылки в сервис
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalLinkDto {

    /**
     * имя ссылки оригинальное(отправленное в сервис)
     */
    @NotBlank(message = "url link must be not empty")
    private String nameOri;

    /**
     * Количество вызовов ссылки
     */
    private int calls;
}
