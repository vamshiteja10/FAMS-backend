package com.farmassist.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "land_parcels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandParcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "parcel_name", nullable = false, length = 100)
    private String parcelName;

    @Column(name = "survey_number", nullable = false, length = 50)
    private String surveyNumber;

    @Column(name = "total_area_acres", nullable = false, precision = 6, scale = 2)
    private BigDecimal totalAreaAcres;

    @Column(name = "soil_type", nullable = false, length = 50)
    private String soilType; // Alluvial, Black, Red, Sandy, Clay, Loamy

    @Column(name = "irrigation_type", nullable = false, length = 50)
    private String irrigationType; // Drip, Sprinkler, Canal, Borewell, Rainfed

    @Column(name = "village_location", length = 120)
    private String villageLocation;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
