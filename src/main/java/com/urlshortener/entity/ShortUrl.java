package com.urlshortener.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "short_urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    private Long clickCount;

    private LocalDateTime createdAt;

    private LocalDateTime lastAccessedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}