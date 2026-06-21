package com.urlshortener.service;

import com.urlshortener.entity.ShortUrl;
import com.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RedirectService {

    private final ShortUrlRepository shortUrlRepository;

    public String getOriginalUrl(String shortCode) {

        ShortUrl shortUrl =
                shortUrlRepository
                        .findByShortCode(shortCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Short URL not found"));

        shortUrl.setClickCount(
                shortUrl.getClickCount() + 1
        );

        shortUrl.setLastAccessedAt(
                LocalDateTime.now()
        );

        shortUrlRepository.save(shortUrl);

        return shortUrl.getOriginalUrl();
    }
}