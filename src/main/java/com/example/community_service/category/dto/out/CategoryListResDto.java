package com.example.community_service.category.dto.out;

import com.example.community_service.category.entity.CategoryList;
import com.example.community_service.category.vo.out.CategoryListResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListResDto {

    private Integer id;
    private Integer mainCategoryId;
    private String mainCategoryName;
    private Integer subCategoryId;
    private String subCategoryName;

    @Builder
    public CategoryListResDto(
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

    public static CategoryListResDto from(CategoryList categoryList) {
        return CategoryListResDto.builder()
                .id(categoryList.getId())
                .mainCategoryId(categoryList.getMainCategoryId())
                .mainCategoryName(categoryList.getMainCategoryName())
                .subCategoryId(categoryList.getSubCategoryId())
                .subCategoryName(categoryList.getSubCategoryName())
                .build();
    }

    public static CategoryListResVo toVo(CategoryListResDto dto) {
        return CategoryListResVo.builder()
                .id(dto.getId())
                .mainCategoryId(dto.getMainCategoryId())
                .mainCategoryName(dto.getMainCategoryName())
                .subCategoryId(dto.getSubCategoryId())
                .subCategoryName(dto.getSubCategoryName())
                .build();
    }
}
