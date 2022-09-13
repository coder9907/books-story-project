package com.example.booksstory.repository;

import com.example.booksstory.entity.FeatureGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureGroupRepository extends JpaRepository<FeatureGroup, Long> {
}
