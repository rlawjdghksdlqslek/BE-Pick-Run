package com.example.community_service.category.application;

import com.example.community_service.category.dto.in.CategoryListReqDto;
import com.example.community_service.category.dto.out.CategoryListResDto;

import java.util.List;

public interface CategoryListService {
    void createCategoryList(CategoryListReqDto dto);
    List<CategoryListResDto> getAllMainCategory();
    void deleteCategoryList(Long id);
}
