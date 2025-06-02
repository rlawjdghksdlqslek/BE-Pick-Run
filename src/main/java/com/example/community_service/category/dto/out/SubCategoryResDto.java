package com.example.community_service.category.dto.out;

import com.example.community_service.category.entity.SubCategory;
import com.example.community_service.category.vo.out.SubCategoryResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResDto {

    private Long id;
    private String name;
    private String color;

    @Builder
    public SubCategoryResDto(Long id, String name, String color)
    {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static SubCategoryResDto from(SubCategory subCategory) {
        return SubCategoryResDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .color(subCategory.getColor())
                .build();
    }

    public static SubCategoryResVo toVo(SubCategoryResDto dto) {
        return SubCategoryResVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .color(dto.getColor())
                .build();
    }
}
