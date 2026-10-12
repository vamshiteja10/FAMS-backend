package com.farmassist.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cultivation_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CultivationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType; // LAND_PREPARATION, SOWING, IRRIGATION, FERTILIZER, PEST_CONTROL, WEEDING, HARVESTING

    @Column(name = "activity_date", nullable = false)
    private LocalDate activityDate;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(name = "inputs_used", length = 150)
    private String inputsUsed;

    @Column(name = "quantity_used", length = 50)
    private String quantityUsed;

    @Column(name = "cost_incurred", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal costIncurred = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
