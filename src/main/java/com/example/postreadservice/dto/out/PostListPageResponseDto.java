package com.example.postreadservice.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListPageResponseDto {

    private List<PostSummaryResDto> posts;
    private int page;
    private int size;
    private boolean hasNext;

    @Builder
    public PostListPageResponseDto(
            List<PostSummaryResDto> posts,
            int page,
            int size,
            boolean hasNext
    ) {
        this.posts = posts;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
    }
}
