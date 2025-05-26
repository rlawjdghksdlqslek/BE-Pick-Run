package com.example.community_service.post.application;

import com.example.community_service.post.dto.in.PostCreateReqDto;
import com.example.community_service.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public void createPost(PostCreateReqDto postCreateReqDto) {
        postRepository.save(postCreateReqDto.toEntity());
    }
}
