package com.farmassist.service;

import com.farmassist.dto.CultivationRecordDto;

import java.util.List;

public interface CultivationRecordService {
    List<CultivationRecordDto> getAllRecords();
    List<CultivationRecordDto> getRecordsByCropId(Long cropId);
    CultivationRecordDto getRecordById(Long id);
    CultivationRecordDto createRecord(CultivationRecordDto dto);
    CultivationRecordDto updateRecord(Long id, CultivationRecordDto dto);
    void deleteRecord(Long id);
}
