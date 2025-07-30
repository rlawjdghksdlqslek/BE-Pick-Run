package com.example.post_service.category.dto.in;

import com.example.post_service.category.entity.SubCategory;
import com.example.post_service.category.vo.in.SubCategoryReqVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryReqDto {

    private String subCategoryName;
    private String color;

    @Builder
    public SubCategoryReqDto(String subCategoryName, String color) {
        this.subCategoryName = subCategoryName;
        this.color = color;
    }

    public static SubCategoryReqDto from(SubCategoryReqVo subCategoryReqVo) {
        return SubCategoryReqDto.builder()
                .subCategoryName(subCategoryReqVo.getName())
                .color(subCategoryReqVo.getColor())
                .build();
    }

    public SubCategory toEntity() {
        return SubCategory.builder()
                .name(subCategoryName)
                .color(color)
                .build();
    }
}
