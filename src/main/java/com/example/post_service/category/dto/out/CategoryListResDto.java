package com.example.post_service.category.dto.out;

import com.example.post_service.category.entity.CategoryList;
import com.example.post_service.category.vo.out.CategoryListResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListResDto {

    private Long id;
    private Long mainCategoryId;
    private String mainCategoryName;
    private Long subCategoryId;
    private String subCategoryName;
    private String subCategoryColor;

    @Builder
    public CategoryListResDto(
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

    public static CategoryListResDto from(CategoryList categoryList) {
        return CategoryListResDto.builder()
                .id(categoryList.getId())
                .mainCategoryId(categoryList.getMainCategoryId())
                .mainCategoryName(categoryList.getMainCategoryName())
                .subCategoryId(categoryList.getSubCategoryId())
                .subCategoryName(categoryList.getSubCategoryName())
                .subCategoryColor(categoryList.getSubCategoryColor())
                .build();
    }

    public static CategoryListResVo toVo(CategoryListResDto dto) {
        return CategoryListResVo.builder()
                .id(dto.getId())
                .mainCategoryId(dto.getMainCategoryId())
                .mainCategoryName(dto.getMainCategoryName())
                .subCategoryId(dto.getSubCategoryId())
                .subCategoryName(dto.getSubCategoryName())
                .subCategoryColor(dto.getSubCategoryColor())
                .build();
    }
}
