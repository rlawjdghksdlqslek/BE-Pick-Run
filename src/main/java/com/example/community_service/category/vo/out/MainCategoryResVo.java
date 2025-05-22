package com.example.community_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResVo {
    private String name;

    @Builder
    public MainCategoryResVo(String name) {
        this.name = name;
    }
}
