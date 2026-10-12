package com.farmassist.controller;

import com.farmassist.dto.LandParcelDto;
import com.farmassist.service.LandParcelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/land")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LandParcelController {

    private final LandParcelService landParcelService;

    @GetMapping
    public ResponseEntity<List<LandParcelDto>> getAllLandParcels() {
        return ResponseEntity.ok(landParcelService.getAllLandParcels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandParcelDto> getLandParcelById(@PathVariable Long id) {
        return ResponseEntity.ok(landParcelService.getLandParcelById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LandParcelDto>> getLandParcelsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(landParcelService.getLandParcelsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<LandParcelDto> createLandParcel(@Valid @RequestBody LandParcelDto dto) {
        LandParcelDto created = landParcelService.createLandParcel(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandParcelDto> updateLandParcel(
            @PathVariable Long id,
            @Valid @RequestBody LandParcelDto dto) {
        LandParcelDto updated = landParcelService.updateLandParcel(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandParcel(@PathVariable Long id) {
        landParcelService.deleteLandParcel(id);
        return ResponseEntity.noContent().build();
    }
}
