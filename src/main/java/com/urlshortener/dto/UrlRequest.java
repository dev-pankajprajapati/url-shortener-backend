package com.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UrlRequest {

    @NotBlank
    private String originalUrl;
}