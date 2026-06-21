package com.urlshortener.controller;

import com.urlshortener.service.RedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final RedirectService redirectService;

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                redirectService
                        .getOriginalUrl(shortCode);

        return ResponseEntity
                .status(302)
                .header(
                        HttpHeaders.LOCATION,
                        originalUrl
                )
                .build();
    }
}