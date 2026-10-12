package com.farmassist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmingResourceDto {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Category is required (SEEDS, FERTILIZERS, PESTICIDES, MACHINERY, GOVT_SCHEME)")
    private String category;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String recommendedDosage;
    private String contactOrLink;
    private String status;
    private LocalDateTime createdAt;
}
