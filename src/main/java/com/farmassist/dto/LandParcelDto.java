package com.farmassist.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandParcelDto {

    private Long id;

    @NotNull(message = "Farmer / User ID is required")
    private Long userId;

    private String farmerName;

    @NotBlank(message = "Parcel name or identifier is required")
    private String parcelName;

    @NotBlank(message = "Survey number is required")
    private String surveyNumber;

    @NotNull(message = "Total area in acres is required")
    @DecimalMin(value = "0.01", message = "Land area must be greater than 0")
    private BigDecimal totalAreaAcres;

    @NotBlank(message = "Soil type is required (e.g., Black, Alluvial, Red)")
    private String soilType;

    @NotBlank(message = "Irrigation type is required (e.g., Drip, Sprinkler, Canal)")
    private String irrigationType;

    private String villageLocation;
    private LocalDateTime createdAt;
}
