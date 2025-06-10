package com.example.post_service.category.application;

import com.example.post_service.category.dto.in.CategoryListReqDto;
import com.example.post_service.category.dto.out.CategoryListResDto;
import com.example.post_service.category.entity.CategoryList;
import com.example.post_service.category.entity.MainCategory;
import com.example.post_service.category.entity.SubCategory;
import com.example.post_service.category.infrastructure.CategoryListRepository;
import com.example.post_service.category.infrastructure.MainCategoryRepository;
import com.example.post_service.category.infrastructure.SubCategoryRepository;
import com.example.post_service.common.exception.BaseException;
import com.example.post_service.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.post_service.common.response.BaseResponseStatus.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryListServiceImpl implements CategoryListService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryListRepository categoryListRepository;

    @Transactional
    @Override
    public void createCategoryList(CategoryListReqDto dto) {
        boolean exists = categoryListRepository.existsByMainCategoryIdAndSubCategoryId(
                dto.getMainCategoryId(), dto.getSubCategoryId()
        );

        if (exists) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_CATEGORY_LIST);
        }

        MainCategory mainCategory = mainCategoryRepository.findById(dto.getMainCategoryId())
                .orElseThrow(() -> new BaseException(CATEGORY_NOT_FOUND));

        SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new BaseException(CATEGORY_NOT_FOUND));

        CategoryList categoryList = dto.of(mainCategory, subCategory);

        categoryListRepository.save(categoryList);
    }

    @Override
    public List<CategoryListResDto> getAllMainCategory() {
        return categoryListRepository.findAll()
                .stream()
                .map(CategoryListResDto::from)
                .toList();
    }

    @Transactional
    @Override
    public void deleteCategoryList(Long id) {
        categoryListRepository.deleteById(id);
    }

    public List<CategoryListResDto> getAllCategoryListByMainCategory(Long mainCategoryId) {
        return categoryListRepository.findAllByMainCategoryId(mainCategoryId)
                .stream()
                .map(CategoryListResDto::from)
                .toList();
    }
}
