package com.example.community_service.category.application;

import com.example.community_service.category.dto.in.MainCategoryReqDto;
import com.example.community_service.category.dto.out.MainCategoryResDto;
import com.example.community_service.category.entity.MainCategory;
import com.example.community_service.category.infrastructure.MainCategoryRepository;
import com.example.community_service.common.entity.BaseResponseStatus;
import com.example.community_service.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<MainCategoryResDto> getAllMainCategory() {
        return mainCategoryRepository.findAll()
                .stream()
                .map(MainCategoryResDto::from)
                .toList();
    }

    @Transactional
    @Override
    public void updateMainCategory(Long id, MainCategoryReqDto dto) {
        MainCategory mainCategory = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        mainCategory.updateName(dto.getMainCategoryName());
    }

    @Transactional
    @Override
    public void deleteMainCategory(Long mainCategoryId) {
        MainCategory mainCategory = mainCategoryRepository.findById(mainCategoryId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND)
        );

        mainCategoryRepository.delete(mainCategory);
    }
}
