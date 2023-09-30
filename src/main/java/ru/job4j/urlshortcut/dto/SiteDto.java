package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * SiteDto Модель содержит поле с URL адресом сайта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;

    /**
     * URL сайта
     */
    @NotBlank(message = "Value must be not empty")
    private String siteURL;
}
