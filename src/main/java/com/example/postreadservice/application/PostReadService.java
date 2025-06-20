package com.example.postreadservice.application;

import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.entity.PostSortType;
import com.example.postreadservice.kafka.event.PostCreatedEvent;
import jakarta.servlet.http.HttpServletRequest;

public interface PostReadService {
    void createPostRead(PostCreatedEvent postCreatedEvent);

    PostReadModelResDto getPostRead(String postUuid, String memberUuid, HttpServletRequest request);

    PostListPageResponseDto getPostBySort(
            Long mainCategoryId, Long subCategoryId, int page, int size, PostSortType postSortTyp);
}
