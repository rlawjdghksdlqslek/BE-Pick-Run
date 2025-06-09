package com.example.postreadservice.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListPageResponseDto {

    private List<PostSummaryResDto> posts;
    private long page;
    private long size;
    private boolean hasNext;
    private long totalPage;
    private long totalElements;

    @Builder
    public PostListPageResponseDto(
            List<PostSummaryResDto> posts,
            long page,
            long size,
            boolean hasNext,
            long totalElements,
            long totalPage
    ) {
        this.posts = posts;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }
}
