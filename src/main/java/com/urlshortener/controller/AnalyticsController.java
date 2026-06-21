package com.urlshortener.controller;

import com.urlshortener.dto.DashboardResponse;
import com.urlshortener.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public DashboardResponse dashboard() {

        return analyticsService.getDashboard();
    }
}