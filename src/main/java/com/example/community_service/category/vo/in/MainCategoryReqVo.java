package com.example.community_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryReqVo {
    private String name;

    @Builder
    public MainCategoryReqVo(String name) {
        this.name = name;
    }
}
