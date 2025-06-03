package com.example.community_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryReqVo {
    private String name;
    private String color;

    @Builder
    public SubCategoryReqVo(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
