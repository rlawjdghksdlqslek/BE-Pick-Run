package com.example.community_service.category.dto.in;

import com.example.community_service.category.entity.SubCategory;
import com.example.community_service.category.vo.in.SubCategoryReqVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryReqDto {

    private String subCategoryName;

    @Builder
    public SubCategoryReqDto(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public static SubCategoryReqDto from(SubCategoryReqVo subCategoryReqVo) {
        return SubCategoryReqDto.builder()
                .subCategoryName(subCategoryReqVo.getName())
                .build();
    }

    public SubCategory toEntity() {
        return SubCategory.builder()
                .name(subCategoryName)
                .build();
    }
}
