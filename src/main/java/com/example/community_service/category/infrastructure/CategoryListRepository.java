package com.example.community_service.category.infrastructure;

import com.example.community_service.category.entity.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryListRepository extends JpaRepository<CategoryList, Integer> {
    boolean existsByMainCategoryIdAndSubCategoryId(Integer mainCategoryId, Integer subCategoryId);

}
