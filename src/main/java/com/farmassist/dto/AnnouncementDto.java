package com.farmassist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Category is required")
    private String category; // WEATHER_ALERT, PEST_WARNING, GOVT_SCHEME, MARKET_UPDATE, GENERAL

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private String urgency; // LOW, MEDIUM, HIGH, EMERGENCY
    private String postedBy;
    private LocalDateTime createdAt;
}
