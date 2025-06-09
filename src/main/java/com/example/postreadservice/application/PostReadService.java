package com.example.postreadservice.application;

import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.dto.out.PostSummaryResDto;
import com.example.postreadservice.entity.PostSortType;
import com.example.postreadservice.kafka.event.PostCreatedEvent;

import java.util.List;

public interface PostReadService {
    void createPostRead(PostCreatedEvent postCreatedEvent);

    PostReadModelResDto getPostRead(String postUuid);

    PostListPageResponseDto getPostBySort(Long categoryListId, int page, int size, PostSortType postSortTyp);
}
