package com.example.community_service.category.dto.out;

import com.example.community_service.category.entity.MainCategory;
import com.example.community_service.category.vo.out.MainCategoryResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResDto {

    private String name;

    @Builder
    public MainCategoryResDto(String name) {
        this.name = name;
    }

    public static MainCategoryResDto from(MainCategory category) {
        return MainCategoryResDto.builder()
                .name(category.getName())
                .build();
    }

    public static MainCategoryResVo toVo(MainCategoryResDto dto) {
        return MainCategoryResVo.builder()
                .name(dto.getName())
                .build();
    }
}
