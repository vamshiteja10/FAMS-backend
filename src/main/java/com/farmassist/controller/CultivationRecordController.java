package com.farmassist.controller;

import com.farmassist.dto.CultivationRecordDto;
import com.farmassist.service.CultivationRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CultivationRecordController {

    private final CultivationRecordService recordService;

    @GetMapping
    public ResponseEntity<List<CultivationRecordDto>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CultivationRecordDto> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<CultivationRecordDto>> getRecordsByCropId(@PathVariable Long cropId) {
        return ResponseEntity.ok(recordService.getRecordsByCropId(cropId));
    }

    @PostMapping
    public ResponseEntity<CultivationRecordDto> createRecord(@Valid @RequestBody CultivationRecordDto dto) {
        CultivationRecordDto created = recordService.createRecord(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CultivationRecordDto> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody CultivationRecordDto dto) {
        CultivationRecordDto updated = recordService.updateRecord(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
