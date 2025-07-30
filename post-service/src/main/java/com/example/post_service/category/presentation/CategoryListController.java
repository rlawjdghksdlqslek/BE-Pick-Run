package com.example.post_service.category.presentation;

import com.example.post_service.category.application.CategoryListServiceImpl;
import com.example.post_service.category.dto.in.CategoryListReqDto;
import com.example.post_service.category.dto.out.CategoryListResDto;
import com.example.post_service.category.vo.in.CategoryListReqVo;
import com.example.post_service.category.vo.out.CategoryListResVo;
import com.example.post_service.common.entity.BaseResponseEntity;
import com.example.post_service.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CategoryList", description = "CategoryList 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category-list")
@Slf4j
public class CategoryListController {

    private final CategoryListServiceImpl categoryListService;

    @Operation(summary = "카테고리 리스트 생성")
    @PostMapping
    public BaseResponseEntity<Void> createCategoryList(@RequestBody CategoryListReqVo categoryListReqVo) {
        categoryListService.createCategoryList(CategoryListReqDto.from(categoryListReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "카테고리 리스트 전체 조회")
    @GetMapping
    public BaseResponseEntity<List<CategoryListResVo>> getAllCategoryList() {
        return new BaseResponseEntity<>(
                categoryListService.getAllMainCategory()
                        .stream()
                        .map(CategoryListResDto::toVo)
                        .toList());
    }

    @Operation(summary = "카테고리 리스트 단건 조회")
    @GetMapping("/{id}")
    public BaseResponseEntity<CategoryListResDto> getCategoryList(@PathVariable Long id) {
        return new BaseResponseEntity<>(categoryListService.getCategoryList(id));
    }

    @Operation(summary = "카테고리 리스트 삭제")
    @DeleteMapping("/{id}")
    public BaseResponseEntity<Void> deleteCategoryList(@PathVariable Long id) {
        categoryListService.deleteCategoryList(id);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "메인 카테고리 ID에 해당하는 하위 categoryList 조회")
    @GetMapping("/main/{id}")
    public BaseResponseEntity<List<CategoryListResDto>> getCategoryListByMainCategory(@PathVariable Long id) {
        return new BaseResponseEntity<>(categoryListService.getAllCategoryListByMainCategory(id));
    }
}
