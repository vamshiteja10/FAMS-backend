package com.farmassist.dto;

import jakarta.validation.constraints.DecimalMin;
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
public class CropDto {

    private Long id;

    @NotNull(message = "Land Parcel must be selected")
    private Long landParcelId;

    private String landParcelName;

    @NotNull(message = "Farmer / User ID is required")
    private Long userId;

    private String farmerName;

    @NotBlank(message = "Crop name is required (e.g., Paddy, Cotton, Wheat)")
    private String cropName;

    private String variety;

    @NotBlank(message = "Season is required (Kharif, Rabi, Zaid)")
    private String season;

    @NotNull(message = "Sowing date is required")
    private LocalDate sowingDate;

    @NotNull(message = "Expected harvest date is required")
    private LocalDate expectedHarvestDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Expected yield must be positive")
    private BigDecimal expectedYieldQuintals;

    private String status; // SOWN, GROWING, FLOWERING, HARVESTED, DAMAGED

    private String notes;

    private LocalDateTime createdAt;
}
