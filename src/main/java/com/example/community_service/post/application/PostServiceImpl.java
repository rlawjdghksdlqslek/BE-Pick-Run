package com.example.community_service.post.application;

import com.example.community_service.post.dto.in.PostCreateReqDto;
import com.example.community_service.post.dto.in.PostUpdateReqDto;
import com.example.community_service.post.entity.Post;
import com.example.community_service.post.infrastructure.PostRepository;
import com.example.community_service.post.vo.in.PostUpdateReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public void createPost(PostCreateReqDto postCreateReqDto) {
        postRepository.save(postCreateReqDto.toEntity());
    }

    @Transactional
    @Override
    public void updatePost(String memberUuid, String postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        validatePostOwner(memberUuid, post);

        post.update(
                postUpdateReqDto.getTitle(),
                postUpdateReqDto.getContents(),
                postUpdateReqDto.getCategoryListId(),
                postUpdateReqDto.getImages()
        );
        postRepository.save(post);
    }

    private void validatePostOwner(String memberUuid, Post post) {
        if (!post.getMemberUuid().equals(memberUuid)) {
            throw new SecurityException("게시글 수정 권한이 없습니다.");
        }
    }
}
