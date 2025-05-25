package com.example.community_service.category.application;

import com.example.community_service.category.dto.in.SubCategoryReqDto;
import com.example.community_service.category.dto.out.SubCategoryResDto;

import java.util.List;

public interface SubCategoryService {

    void createSubCategory(SubCategoryReqDto mainCategoryReqDto);

    List<SubCategoryResDto> getAllSubCategory();
    void updateSubCategory(Long id, SubCategoryReqDto from);
    void deleteSubCategory(Long subCategoryId);
}
