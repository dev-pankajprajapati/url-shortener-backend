package com.urlshortener.service;

import com.urlshortener.dto.DashboardResponse;
import com.urlshortener.entity.User;
import com.urlshortener.repository.ShortUrlRepository;
import com.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UserRepository userRepository;
    private final ShortUrlRepository shortUrlRepository;

    public DashboardResponse getDashboard() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        Long totalUrls =
                shortUrlRepository.countByUser(user);

        Long totalClicks =
                shortUrlRepository
                        .findByUser(user)
                        .stream()
                        .mapToLong(
                                ShortUrl ->
                                        ShortUrl.getClickCount()
                        )
                        .sum();

        return new DashboardResponse(
                totalUrls,
                totalClicks
        );
    }
}