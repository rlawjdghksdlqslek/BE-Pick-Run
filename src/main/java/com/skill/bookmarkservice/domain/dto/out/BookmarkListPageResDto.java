package com.skill.bookmarkservice.domain.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BookmarkListPageResDto {

    private List<String> postUuid;
    private long page;
    private long size;
    private boolean hasNext;
    private long totalPages;
    private long totalElements;

    @Builder
    public BookmarkListPageResDto(
            List<String> postUuid, long page, long size, boolean hasNext, long totalPages, long totalElements) {
        this.postUuid = postUuid;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
