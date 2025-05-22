package com.example.community_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResVo {
    private String name;

    @Builder
    public SubCategoryResVo(String name) {
        this.name = name;
    }
}
