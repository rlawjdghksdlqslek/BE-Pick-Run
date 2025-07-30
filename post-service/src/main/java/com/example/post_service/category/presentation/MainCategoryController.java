package com.example.post_service.category.presentation;

import com.example.post_service.category.application.MainCategoryService;
import com.example.post_service.category.dto.in.MainCategoryReqDto;
import com.example.post_service.category.dto.out.MainCategoryResDto;
import com.example.post_service.category.dto.out.SimpleSubCategoryResDto;
import com.example.post_service.category.vo.in.MainCategoryReqVo;
import com.example.post_service.category.vo.out.MainCategoryResVo;
import com.example.post_service.category.vo.out.MainCategoryWithSubCategoriesResVo;
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

    @Operation(summary = "메인 카테고리 단건 조회")
    @GetMapping("/main/{id}")
    public BaseResponseEntity<MainCategoryResVo> getMainCategory(
            @PathVariable Long id
    ) {
        return new BaseResponseEntity<>(
                MainCategoryResVo.of(mainCategoryService.getMainCategoryById(id)));
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
    @GetMapping("/main/{id}/sub-categories")
    public BaseResponseEntity<List<SimpleSubCategoryResDto>> getSubCategoriesByMainCategoryId (@PathVariable Long id) {
        return new BaseResponseEntity<>(mainCategoryService.getSubCategoriesByMainCategoryId(id));
    }

    @Operation(
            summary = "메인 카테고리 + 서브카테고리 목록 조회",
            description = """
            모든 메인 카테고리와 그에 소속된 서브카테고리 목록을 함께 조회합니다.

            [요청 경로]
            - GET /api/v1/chat-room/main/with-subcategories

            [응답 필드]
            - mainCategoryId, mainCategoryName, subCategories: [{id, name, ...}]

            [처리 로직]
            - 모든 메인 카테고리를 조회한 뒤, 각각의 하위 카테고리를 포함하여 반환합니다.
        """
    )
    @GetMapping("/main/with-subcategories")
    public BaseResponseEntity<List<MainCategoryWithSubCategoriesResVo>> getAllMainCategoriesWithSubCategories() {
        return new BaseResponseEntity<>(
                mainCategoryService.getAllMainCategoriesWithSubCategories()
                        .stream()
                        .map(MainCategoryWithSubCategoriesResVo::from)
                        .toList());
    }
}
