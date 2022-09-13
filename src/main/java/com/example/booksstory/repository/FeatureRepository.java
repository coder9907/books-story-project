package com.example.booksstory.repository;

import com.example.booksstory.entity.Feature;
import com.example.booksstory.entity.FeatureGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
