package com.example.post_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResVo {
    private Long id;
    private String name;
    private String iconUrl;
    private String alt;

    @Builder
    public MainCategoryResVo(Long id, String name, String iconUrl, String alt) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.alt = alt;
    }
}
