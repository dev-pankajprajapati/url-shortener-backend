package com.urlshortener.controller;

import com.urlshortener.dto.UrlRequest;
import com.urlshortener.dto.UrlResponse;
import com.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public UrlResponse createShortUrl(
            @Valid
            @RequestBody UrlRequest request) {

        return urlService.createShortUrl(request);
    }

    @GetMapping("/my-urls")
    public List<UrlResponse> getMyUrls() {

        return urlService.getMyUrls();
    }

    @DeleteMapping("/{id}")
    public String deleteUrl(
            @PathVariable Long id) {

        urlService.deleteUrl(id);

        return "URL Deleted Successfully";
    }
}