package com.farmassist.controller;

import com.farmassist.dto.FarmingResourceDto;
import com.farmassist.service.FarmingResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FarmingResourceController {

    private final FarmingResourceService resourceService;

    @GetMapping
    public ResponseEntity<List<FarmingResourceDto>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmingResourceDto> getResourceById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FarmingResourceDto>> getResourcesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(resourceService.getResourcesByCategory(category.toUpperCase()));
    }

    @PostMapping
    public ResponseEntity<FarmingResourceDto> createResource(@Valid @RequestBody FarmingResourceDto dto) {
        FarmingResourceDto created = resourceService.createResource(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmingResourceDto> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody FarmingResourceDto dto) {
        FarmingResourceDto updated = resourceService.updateResource(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}
