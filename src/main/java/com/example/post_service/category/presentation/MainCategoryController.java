package com.example.post_service.category.presentation;

import com.example.post_service.category.application.MainCategoryService;
import com.example.post_service.category.dto.in.MainCategoryReqDto;
import com.example.post_service.category.dto.out.MainCategoryResDto;
import com.example.post_service.category.dto.out.SimpleSubCategoryResDto;
import com.example.post_service.category.vo.in.MainCategoryReqVo;
import com.example.post_service.category.vo.out.MainCategoryResVo;
import com.example.post_service.common.entity.BaseResponseEntity;
import com.example.post_service.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MainCategory", description = "MainCategory 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @Operation(summary = "메인 카테고리 생성")
    @PostMapping("/main")
    public BaseResponseEntity<Void> createMainCategory(@RequestBody MainCategoryReqVo mainCategoryReqVo) {
        mainCategoryService.createMainCategory(MainCategoryReqDto.from(mainCategoryReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "메인 카테고리 전체 조회")
    @GetMapping("/main")
    public BaseResponseEntity<List<MainCategoryResVo>> getAllMainCategory() {
        return new BaseResponseEntity<>(
                mainCategoryService.getAllMainCategory()
                        .stream()
                        .map(MainCategoryResDto::toVo)
                        .toList());
    }

    @Operation(summary = "메인 카테고리 수정")
    @PutMapping("/main/{id}")
    public BaseResponseEntity<Void> updateMainCategory(
            @PathVariable Long id,
            @RequestBody MainCategoryReqVo mainCategoryReqVo
    ) {
        mainCategoryService.updateMainCategory(id, MainCategoryReqDto.from(mainCategoryReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "메인 카테고리 삭제")
    @DeleteMapping("/main/{id}")
    public BaseResponseEntity<Void> deleteMainCategory(@PathVariable Long id) {
        mainCategoryService.deleteMainCategory(id);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "메인 카테고리에 속한 서브 카테고리 전체 조회")
    @GetMapping("/main/{id}")
    public BaseResponseEntity<List<SimpleSubCategoryResDto>> getMainCategory(@PathVariable Long id) {
        return new BaseResponseEntity<>(mainCategoryService.getSubCategoriesByMainCategoryId(id));
    }
}
