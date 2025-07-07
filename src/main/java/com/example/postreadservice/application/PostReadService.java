package com.example.postreadservice.application;

import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.entity.PostSortType;
import com.example.postreadservice.kafka.event.PostCreatedEvent;
import com.example.postreadservice.kafka.event.PostDeletedEvent;
import com.example.postreadservice.kafka.event.PostUpdatedEvent;
import jakarta.servlet.http.HttpServletRequest;

public interface PostReadService {
    void createPostRead(PostCreatedEvent postCreatedEvent);

    void updatePostRead(PostUpdatedEvent postUpdatedEvent);

    void softDeletePostRead(PostDeletedEvent postDeletedEvent);

    PostReadModelResDto getPostRead(String postUuid, String memberUuid, HttpServletRequest request);

    PostListPageResponseDto getPostBySort(
            Long mainCategoryId, Long subCategoryId, int page, int size, PostSortType postSortTyp);

    PostListPageResponseDto searchPostsByTitle(String titleKeyword, int page, int size);
}
