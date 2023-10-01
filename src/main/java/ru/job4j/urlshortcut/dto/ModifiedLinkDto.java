package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DTO для формирования преобразованной ссылки (кода) из сервиса.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifiedLinkDto {

    /**
     * имя ссылки(код) модифицированное(отправленное из сервиса)
     */
    @NotBlank(message = "url link must be not empty")
    private String nameMod;
}
