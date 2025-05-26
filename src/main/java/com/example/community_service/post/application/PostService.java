package com.example.community_service.post.application;

import com.example.community_service.post.dto.in.PostCreateReqDto;

public interface PostService {

    void createPost(PostCreateReqDto postCreateReqDto);
}
