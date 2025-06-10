package com.example.post_service.category.application;

import com.example.post_service.category.dto.in.SubCategoryReqDto;
import com.example.post_service.category.dto.out.SubCategoryResDto;

import java.util.List;

public interface SubCategoryService {

    void createSubCategory(SubCategoryReqDto mainCategoryReqDto);

    List<SubCategoryResDto> getAllSubCategory();
    void updateSubCategory(Long id, SubCategoryReqDto subCategoryReqDto);
    void deleteSubCategory(Long subCategoryId);
}
