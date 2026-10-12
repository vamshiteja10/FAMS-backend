package com.farmassist.repository;

import com.farmassist.entity.LandParcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandParcelRepository extends JpaRepository<LandParcel, Long> {
    List<LandParcel> findByUserId(Long userId);
}
