package com.example.postreadservice.entity;

import org.springframework.data.domain.Sort;

public enum PostSortType {
    POPULAR("viewCount"),
    RECENT("createdAt");

    private final String sortField;

    PostSortType(String sortField) {
        this.sortField = sortField;
    }

    public Sort getSort() {
        return Sort.by(Sort.Direction.DESC, this.sortField);
    }
}
