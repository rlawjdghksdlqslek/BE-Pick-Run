package com.example.community_service.category.dto.out;

import com.example.community_service.category.entity.MainCategory;
import com.example.community_service.category.vo.out.MainCategoryResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResDto {

    private Long id;
    private String name;

    @Builder
    public MainCategoryResDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MainCategoryResDto from(MainCategory category) {
        return MainCategoryResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static MainCategoryResVo toVo(MainCategoryResDto dto) {
        return MainCategoryResVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
