package com.example.post_service.category.presentation;

import com.example.post_service.category.application.SubCategoryService;
import com.example.post_service.category.dto.in.SubCategoryReqDto;
import com.example.post_service.category.dto.out.SubCategoryResDto;
import com.example.post_service.category.vo.in.SubCategoryReqVo;
import com.example.post_service.category.vo.out.SubCategoryResVo;
import com.example.post_service.common.entity.BaseResponseEntity;
import com.example.post_service.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SubCategory", description = "SubCategory 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "서브 카테고리 생성")
    @PostMapping("/sub")
    public BaseResponseEntity<Void> createSubCategory(@RequestBody SubCategoryReqVo vo) {
        subCategoryService.createSubCategory(SubCategoryReqDto.from(vo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "서브 카테고리 전체 조회")
    @GetMapping("/sub")
    public BaseResponseEntity<List<SubCategoryResVo>> getAllSubCategory() {
        return new BaseResponseEntity<>(
                subCategoryService.getAllSubCategory()
                        .stream()
                        .map(SubCategoryResDto::toVo)
                        .toList());
    }

    @Operation(summary = "서브 카테고리 수정")
    @PutMapping("/sub/{id}")
    public BaseResponseEntity<Void> updateSubCategory(
            @PathVariable Long id,
            @RequestBody SubCategoryReqVo subCategoryReqVo
    ) {
        subCategoryService.updateSubCategory(id, SubCategoryReqDto.from(subCategoryReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "서브 카테고리 삭제")
    @DeleteMapping("/sub/{id}")
    public BaseResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
