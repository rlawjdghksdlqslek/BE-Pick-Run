package com.example.post_service.post.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateReqVo {
    private String title;
    private String contents;
    private Long mainCategoryId;
    private Long subCategoryId;
}