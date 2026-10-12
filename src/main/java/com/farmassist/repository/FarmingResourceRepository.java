package com.farmassist.repository;

import com.farmassist.entity.FarmingResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmingResourceRepository extends JpaRepository<FarmingResource, Long> {
    List<FarmingResource> findByCategory(String category);
    List<FarmingResource> findByStatus(String status);
}
