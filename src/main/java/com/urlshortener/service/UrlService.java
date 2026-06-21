package com.urlshortener.service;

import com.urlshortener.dto.UrlRequest;
import com.urlshortener.dto.UrlResponse;
import com.urlshortener.entity.ShortUrl;
import com.urlshortener.entity.User;
import com.urlshortener.repository.ShortUrlRepository;
import com.urlshortener.repository.UserRepository;
import com.urlshortener.util.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {

    @Value("${app.base-url}")
    private String baseUrl;

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;


    public UrlResponse createShortUrl(
            UrlRequest request) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        String shortCode;

        do {
            shortCode =
                    ShortCodeGenerator.generateCode(6);
        }
        while (
                shortUrlRepository
                        .findByShortCode(shortCode)
                        .isPresent()
        );

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(request.getOriginalUrl())
                .shortCode(shortCode)
                .clickCount(0L)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        shortUrlRepository.save(shortUrl);

        return mapToResponse(shortUrl);
    }

    public List<UrlResponse> getMyUrls() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        return shortUrlRepository
                .findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UrlResponse mapToResponse(
            ShortUrl shortUrl) {

        return UrlResponse.builder()
                .id(shortUrl.getId())
                .originalUrl(shortUrl.getOriginalUrl())
                .shortCode(shortUrl.getShortCode())
                .shortUrl(
                        baseUrl + "/r/"
                                + shortUrl.getShortCode()
                )
                .clickCount(shortUrl.getClickCount())
                .build();
    }

    public void deleteUrl(Long id) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        ShortUrl shortUrl =
                shortUrlRepository.findById(id)
                        .orElseThrow();

        if (!shortUrl.getUser()
                .getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot delete this URL"
            );
        }

        shortUrlRepository.delete(shortUrl);
    }
}