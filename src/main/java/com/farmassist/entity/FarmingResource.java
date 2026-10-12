package com.farmassist.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "farming_resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmingResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 50)
    private String category; // SEEDS, FERTILIZERS, PESTICIDES, MACHINERY, GOVT_SCHEME

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "recommended_dosage", length = 150)
    private String recommendedDosage;

    @Column(name = "contact_or_link", length = 255)
    private String contactOrLink;

    @Column(length = 30)
    @Builder.Default
    private String status = "AVAILABLE"; // AVAILABLE, SUBSIDIZED, INFO_ONLY

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
