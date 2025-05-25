package com.example.community_service.category.dto.out;

import com.example.community_service.category.entity.SubCategory;
import com.example.community_service.category.vo.out.SubCategoryResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResDto {

    private String name;

    @Builder
    public SubCategoryResDto(String name) {
        this.name = name;
    }

    public static SubCategoryResDto from(SubCategory subCategory) {
        return SubCategoryResDto.builder()
                .name(subCategory.getName())
                .build();
    }

    public static SubCategoryResVo toVo(SubCategoryResDto dto) {
        return SubCategoryResVo.builder()
                .name(dto.getName())
                .build();
    }
}
