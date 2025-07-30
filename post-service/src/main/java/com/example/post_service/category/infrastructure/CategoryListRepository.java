package com.example.post_service.category.infrastructure;

import com.example.post_service.category.entity.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryListRepository extends JpaRepository<CategoryList, Long> {
    boolean existsByMainCategoryIdAndSubCategoryId(Long mainCategoryId, Long subCategoryId);

    List<CategoryList> findAllByMainCategoryId(Long mainCategoryId);
}
