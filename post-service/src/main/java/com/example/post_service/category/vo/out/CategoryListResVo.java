package com.example.post_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListResVo {

    private Long id;
    private Long mainCategoryId;
    private String mainCategoryName;
    private Long subCategoryId;
    private String subCategoryName;
    private String subCategoryColor;

    @Builder
    public CategoryListResVo(
            Long id,
            Long mainCategoryId,
            String mainCategoryName,
            Long subCategoryId,
            String subCategoryName,
            String subCategoryColor
    ) {
        this.id = id;
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.subCategoryColor = subCategoryColor;
    }
}
