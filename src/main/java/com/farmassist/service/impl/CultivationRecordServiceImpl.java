package com.farmassist.service.impl;

import com.farmassist.dto.CultivationRecordDto;
import com.farmassist.entity.Crop;
import com.farmassist.entity.CultivationRecord;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.CropRepository;
import com.farmassist.repository.CultivationRecordRepository;
import com.farmassist.service.CultivationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CultivationRecordServiceImpl implements CultivationRecordService {

    private final CultivationRecordRepository recordRepository;
    private final CropRepository cropRepository;

    @Override
    public List<CultivationRecordDto> getAllRecords() {
        return recordRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CultivationRecordDto> getRecordsByCropId(Long cropId) {
        return recordRepository.findByCropIdOrderByActivityDateDesc(cropId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CultivationRecordDto getRecordById(Long id) {
        CultivationRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cultivation record not found with id: " + id));
        return mapToDto(record);
    }

    @Override
    @Transactional
    public CultivationRecordDto createRecord(CultivationRecordDto dto) {
        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + dto.getCropId()));

        CultivationRecord record = CultivationRecord.builder()
                .crop(crop)
                .activityType(dto.getActivityType())
                .activityDate(dto.getActivityDate())
                .description(dto.getDescription())
                .inputsUsed(dto.getInputsUsed())
                .quantityUsed(dto.getQuantityUsed())
                .costIncurred(dto.getCostIncurred())
                .observations(dto.getObservations())
                .build();

        return mapToDto(recordRepository.save(record));
    }

    @Override
    @Transactional
    public CultivationRecordDto updateRecord(Long id, CultivationRecordDto dto) {
        CultivationRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cultivation record not found with id: " + id));

        record.setActivityType(dto.getActivityType());
        record.setActivityDate(dto.getActivityDate());
        record.setDescription(dto.getDescription());
        record.setInputsUsed(dto.getInputsUsed());
        record.setQuantityUsed(dto.getQuantityUsed());
        record.setCostIncurred(dto.getCostIncurred());
        record.setObservations(dto.getObservations());

        return mapToDto(recordRepository.save(record));
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cultivation record not found with id: " + id);
        }
        recordRepository.deleteById(id);
    }

    private CultivationRecordDto mapToDto(CultivationRecord record) {
        return CultivationRecordDto.builder()
                .id(record.getId())
                .cropId(record.getCrop().getId())
                .cropName(record.getCrop().getCropName())
                .activityType(record.getActivityType())
                .activityDate(record.getActivityDate())
                .description(record.getDescription())
                .inputsUsed(record.getInputsUsed())
                .quantityUsed(record.getQuantityUsed())
                .costIncurred(record.getCostIncurred())
                .observations(record.getObservations())
                .createdAt(record.getCreatedAt())
                .build();
    }
}
