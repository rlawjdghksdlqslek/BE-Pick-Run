package com.example.post_service.category.dto.in;

import com.example.post_service.category.entity.MainCategory;
import com.example.post_service.category.vo.in.MainCategoryReqVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryReqDto {

    private String mainCategoryName;
    private String iconUrl;
    private String alt;

    @Builder
    public MainCategoryReqDto(
            String mainCategoryName,
            String iconUrl,
            String alt
    ) {
        this.mainCategoryName = mainCategoryName;
        this.iconUrl = iconUrl;
        this.alt = alt;
    }

    public static MainCategoryReqDto from(MainCategoryReqVo mainCategoryReqVo) {
        return MainCategoryReqDto.builder()
                .mainCategoryName(mainCategoryReqVo.getName())
                .iconUrl(mainCategoryReqVo.getIconUrl())
                .alt(mainCategoryReqVo.getAlt())
                .build();
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .name(mainCategoryName)
                .iconUrl(iconUrl)
                .alt(alt)
                .build();
    }
}
