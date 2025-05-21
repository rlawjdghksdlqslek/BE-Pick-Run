package com.example.community_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryReqVo {
    private String name;

    @Builder
    public SubCategoryReqVo(String name) {
        this.name = name;
    }
}
