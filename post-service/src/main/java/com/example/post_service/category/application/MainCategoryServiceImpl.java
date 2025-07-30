package com.example.post_service.category.application;

import com.example.post_service.category.dto.in.MainCategoryReqDto;
import com.example.post_service.category.dto.out.MainCategoryResDto;
import com.example.post_service.category.dto.out.MainCategoryWithSubCategoriesResDto;
import com.example.post_service.category.dto.out.SimpleSubCategoryResDto;
import com.example.post_service.category.entity.CategoryList;
import com.example.post_service.category.entity.MainCategory;
import com.example.post_service.category.infrastructure.CategoryListRepository;
import com.example.post_service.category.infrastructure.MainCategoryRepository;
import com.example.post_service.common.exception.BaseException;
import com.example.post_service.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final CategoryListRepository categoryListRepository;

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

    @Override
    public MainCategoryResDto getMainCategoryById(Long id) {
        MainCategory maincategory = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));
        return MainCategoryResDto.from(maincategory);
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

    @Override
    public List<SimpleSubCategoryResDto> getSubCategoriesByMainCategoryId(Long mainCategoryId) {
        List<CategoryList> list = categoryListRepository.findAllByMainCategoryId(mainCategoryId);

        return list.stream()
                .collect(Collectors.toMap(
                        CategoryList::getSubCategoryId,
                        c -> new SimpleSubCategoryResDto(
                                c.getSubCategoryId(), c.getSubCategoryName(), c.getSubCategoryColor()),
                        (a, b) -> a
                ))
                .values()
                .stream()
                .toList();
    }

    @Override
    public List<MainCategoryWithSubCategoriesResDto> getAllMainCategoriesWithSubCategories() {
        // 모든 메인 카테고리 조회
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();

        // 모든 카테고리 리스트 조회
        List<CategoryList> allCategoryLists = categoryListRepository.findAll();

        // 메인 카테고리 ID별로 서브카테고리 그룹화
        Map<Long, List<CategoryList>> categoryListMap = allCategoryLists.stream()
                .collect(Collectors.groupingBy(CategoryList::getMainCategoryId));

        return mainCategories.stream()
                .map(mainCategory -> {
                    List<CategoryList> categoryLists = categoryListMap.getOrDefault(mainCategory.getId(), List.of());

                    List<MainCategoryWithSubCategoriesResDto.SubCategoryDto> subCategoryDtos = categoryLists.stream()
                            .map(categoryList -> MainCategoryWithSubCategoriesResDto.SubCategoryDto.builder()
                                    .subCategoryId(categoryList.getSubCategoryId())
                                    .subCategoryName(categoryList.getSubCategoryName())
                                    .color(categoryList.getSubCategoryColor())
                                    .build())
                            .toList();

                    return MainCategoryWithSubCategoriesResDto.builder()
                            .mainCategoryId(mainCategory.getId())
                            .mainCategoryName(mainCategory.getName())
                            .iconUrl(mainCategory.getIconUrl())
                            .alt(mainCategory.getAlt())
                            .subCategories(subCategoryDtos)
                            .build();
                })
                .toList();
    }
}
