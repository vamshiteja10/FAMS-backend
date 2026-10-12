package com.farmassist.repository;

import com.farmassist.entity.CultivationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivationRecordRepository extends JpaRepository<CultivationRecord, Long> {
    List<CultivationRecord> findByCropIdOrderByActivityDateDesc(Long cropId);
    List<CultivationRecord> findByActivityType(String activityType);
}
