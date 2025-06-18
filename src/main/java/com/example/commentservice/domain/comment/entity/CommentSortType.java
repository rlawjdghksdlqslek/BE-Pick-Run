package com.example.commentservice.domain.comment.entity;

import org.springframework.data.domain.Sort;

public enum CommentSortType {
    LIKE("likeCount"),
    POPULAR("viewCount"),
    RECENT("createdAt");

    private final String sortField;

    CommentSortType(String sortField) {
        this.sortField = sortField;
    }

    public Sort getSort() {
        return Sort.by(Sort.Direction.DESC, this.sortField);
    }
}
