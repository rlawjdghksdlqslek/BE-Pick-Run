package com.example.community_service.category.dto.in;

import com.example.community_service.category.entity.MainCategory;
import com.example.community_service.category.vo.in.MainCategoryReqVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryReqDto {

    private String mainCategoryName;

    @Builder
    public MainCategoryReqDto(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public static MainCategoryReqDto from(MainCategoryReqVo mainCategoryReqVo) {
        return MainCategoryReqDto.builder()
                .mainCategoryName(mainCategoryReqVo.getName())
                .build();
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .name(mainCategoryName)
                .build();
    }
}
