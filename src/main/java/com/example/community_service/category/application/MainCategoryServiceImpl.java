package com.example.community_service.category.application;

import com.example.community_service.category.dto.in.MainCategoryReqDto;
import com.example.community_service.category.infrastructure.MainCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    @Transactional
    @Override
    public void createMainCategory(MainCategoryReqDto mainCategoryReqDto) {
        mainCategoryRepository.save(mainCategoryReqDto.toEntity());
    }
}
