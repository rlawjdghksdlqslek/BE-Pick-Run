package com.example.commentservice.domain.comment.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentListPageResDto {

    private List<CommentResDto> comments;
    private long page;
    private long size;
    private boolean hasNext;
    private long totalPages;
    private long totalElements;

    @Builder
    public CommentListPageResDto(
            List<CommentResDto> comments, long page, long size, boolean hasNext, long totalPages, long totalElements) {
        this.comments = comments;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
