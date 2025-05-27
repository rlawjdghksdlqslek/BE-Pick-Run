package com.example.community_service.category.application;

import com.example.community_service.category.dto.in.MainCategoryReqDto;
import com.example.community_service.category.dto.out.MainCategoryResDto;

import java.util.List;

public interface MainCategoryService {

    void createMainCategory(MainCategoryReqDto mainCategoryReqDto);

    List<MainCategoryResDto> getAllMainCategory();

    void updateMainCategory(Long id, MainCategoryReqDto mainCategoryReqDto);
    void deleteMainCategory(Long mainCategoryId);
}
