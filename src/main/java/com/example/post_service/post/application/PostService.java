package com.example.post_service.post.application;

import com.example.post_service.post.dto.in.PostCreateReqDto;
import com.example.post_service.post.dto.in.PostUpdateReqDto;
import com.example.post_service.post.dto.out.ExistsPostDto;
import com.example.post_service.post.dto.out.GetPostInfoResDto;

public interface PostService {

    void createPost(PostCreateReqDto postCreateReqDto);

    void updatePost(String memberUuid, String postUuid, PostUpdateReqDto postUpdateReqDto);

    ExistsPostDto existsPost(String postUuid);

    GetPostInfoResDto getPostInfo(String postUuid);
}
