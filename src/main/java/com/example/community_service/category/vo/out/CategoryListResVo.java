package com.example.community_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListResVo {

    private Integer id;
    private Integer mainCategoryId;
    private String mainCategoryName;
    private Integer subCategoryId;
    private String subCategoryName;

    @Builder
    public CategoryListResVo(
            Integer id,
            Integer mainCategoryId,
            String mainCategoryName,
            Integer subCategoryId,
            String subCategoryName
    ) {
        this.id = id;
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }
}
