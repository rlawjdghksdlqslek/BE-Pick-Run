package com.example.post_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryReqVo {
    private String name;
    private String iconUrl;
    private String alt;

    @Builder
    public MainCategoryReqVo(
            String name,
            String iconUrl,
            String alt
    ) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.alt = alt;
    }
}
