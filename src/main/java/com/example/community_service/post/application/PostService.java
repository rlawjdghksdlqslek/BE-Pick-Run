package com.example.community_service.post.application;

import com.example.community_service.post.dto.in.PostCreateReqDto;
import com.example.community_service.post.dto.in.PostUpdateReqDto;
import com.example.community_service.post.dto.out.GetPostInfoResDto;
import com.example.community_service.post.vo.in.PostUpdateReqVo;

public interface PostService {

    void createPost(PostCreateReqDto postCreateReqDto);

    void updatePost(String memberUuid, String postId, PostUpdateReqDto postUpdateReqDto);

    GetPostInfoResDto getPostInfo(String postId);
}
