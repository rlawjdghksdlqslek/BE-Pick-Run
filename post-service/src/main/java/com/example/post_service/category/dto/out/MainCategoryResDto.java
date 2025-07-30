package com.example.post_service.category.dto.out;

import com.example.post_service.category.entity.MainCategory;
import com.example.post_service.category.vo.out.MainCategoryResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResDto {

    private Long id;
    private String name;
    private String iconUrl;
    private String alt;

    @Builder
    public MainCategoryResDto(Long id, String name, String iconUrl, String alt) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.alt = alt;
    }

    public static MainCategoryResDto from(MainCategory category) {
        return MainCategoryResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .iconUrl(category.getIconUrl())
                .alt(category.getAlt())
                .build();
    }

    public static MainCategoryResVo toVo(MainCategoryResDto dto) {
        return MainCategoryResVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .iconUrl(dto.getIconUrl())
                .alt(dto.getAlt())
                .build();
    }
}
