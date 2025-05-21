package com.example.community_service.category.presentation;

import com.example.community_service.category.application.MainCategoryService;
import com.example.community_service.category.dto.in.MainCategoryReqDto;
import com.example.community_service.category.vo.in.MainCategoryReqVo;
import com.example.community_service.common.entity.BaseResponseStatus;
import com.example.community_service.common.response.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MainCategory", description = "MainCategory 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/community-service/api/v1/category")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @Operation(summary = "메인 카테고리 생성")
    @PostMapping("/main")
    public BaseResponseEntity<Void> createMainCategory(@RequestBody MainCategoryReqVo mainCategoryReqVo) {
        mainCategoryService.createMainCategory(MainCategoryReqDto.from(mainCategoryReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
