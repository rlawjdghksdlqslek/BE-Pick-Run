package com.example.post_service.category.infrastructure;

import com.example.post_service.category.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
}
