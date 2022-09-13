package com.example.booksstory.repository;

import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
