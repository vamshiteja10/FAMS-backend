package com.farmassist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CultivationRecordDto {

    private Long id;

    @NotNull(message = "Crop ID must be specified")
    private Long cropId;

    private String cropName;

    @NotBlank(message = "Activity type is required (e.g., SOWING, IRRIGATION, FERTILIZER)")
    private String activityType;

    @NotNull(message = "Activity date is required")
    private LocalDate activityDate;

    @NotBlank(message = "Activity description is required")
    private String description;

    private String inputsUsed;
    private String quantityUsed;
    private BigDecimal costIncurred;
    private String observations;
    private LocalDateTime createdAt;
}
