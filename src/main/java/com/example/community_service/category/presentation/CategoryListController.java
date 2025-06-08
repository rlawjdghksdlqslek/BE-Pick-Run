package com.example.community_service.category.presentation;

import com.example.community_service.category.application.CategoryListServiceImpl;
import com.example.community_service.category.dto.in.CategoryListReqDto;
import com.example.community_service.category.dto.out.CategoryListResDto;
import com.example.community_service.category.vo.in.CategoryListReqVo;
import com.example.community_service.category.vo.out.CategoryListResVo;
import com.example.community_service.common.entity.BaseResponseStatus;
import com.example.community_service.common.response.BaseResponseEntity;
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

    @Operation(summary = "카테고리 리스트 삭제")
    @DeleteMapping("/{id}")
    public BaseResponseEntity<Void> deleteCategoryList(@PathVariable Long id) {
        categoryListService.deleteCategoryList(id);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
