package com.example.post_service.post.application;

import com.example.post_service.post.dto.in.PostCreateReqDto;
import com.example.post_service.post.dto.in.PostUpdateReqDto;
import com.example.post_service.post.dto.out.ExistsPostDto;

public interface PostService {

    void createPost(PostCreateReqDto postCreateReqDto);

    void updatePost(String memberUuid, String postId, PostUpdateReqDto postUpdateReqDto);

    ExistsPostDto existsPost(String postUuid);
}
