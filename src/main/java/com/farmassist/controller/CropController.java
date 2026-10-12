package com.farmassist.controller;

import com.farmassist.dto.CropDto;
import com.farmassist.service.CropService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CropController {

    private final CropService cropService;

    @GetMapping
    public ResponseEntity<List<CropDto>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
        return ResponseEntity.ok(cropService.getCropById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CropDto>> getCropsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cropService.getCropsByUserId(userId));
    }

    @GetMapping("/land/{landParcelId}")
    public ResponseEntity<List<CropDto>> getCropsByLandParcelId(@PathVariable Long landParcelId) {
        return ResponseEntity.ok(cropService.getCropsByLandParcelId(landParcelId));
    }

    @PostMapping
    public ResponseEntity<CropDto> createCrop(@Valid @RequestBody CropDto dto) {
        CropDto created = cropService.createCrop(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropDto> updateCrop(
            @PathVariable Long id,
            @Valid @RequestBody CropDto dto) {
        CropDto updated = cropService.updateCrop(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}
