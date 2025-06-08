package com.example.community_service.post.application;

import com.example.community_service.kafka.event.PostCreatedEvent;
import com.example.community_service.kafka.producer.PostKafkaProducer;
import com.example.community_service.post.dto.in.PostCreateReqDto;
import com.example.community_service.post.dto.in.PostUpdateReqDto;
import com.example.community_service.post.entity.Post;
import com.example.community_service.post.infrastructure.PostRepository;
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
    private final PostKafkaProducer postKafkaProducer;

    @Transactional
    @Override
    public void createPost(PostCreateReqDto postCreateReqDto) {
        Post post = postRepository.save(postCreateReqDto.toEntity());
        PostCreatedEvent postCreatedEvent = PostCreatedEvent.builder()
                .postUuid(post.getPostUuid())
                .memberUuid(post.getMemberUuid())
                .categoryListId(post.getCategoryListId())
                .title(post.getTitle())
                .contents(post.getContents())
                .images(post.getImages())
                .blindStatus(post.isBlindStatus())
                .deletedStatus(post.isDeletedStatus())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        postKafkaProducer.sendPostEvent(postCreatedEvent);
    }

    @Transactional
    @Override
    public void updatePost(
            String memberUuid,
            String postId,
            PostUpdateReqDto postUpdateReqDto
    ) {
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

    private void validatePostOwner(
            String memberUuid,
            Post post
    ) {
        if (!post.getMemberUuid().equals(memberUuid)) {
            throw new SecurityException("게시글 수정 권한이 없습니다.");
        }
    }
}
