package com.example.post_service.post.application;

import com.example.post_service.kafka.event.PostCreatedEvent;
import com.example.post_service.kafka.producer.PostKafkaProducer;
import com.example.post_service.post.dto.in.PostCreateReqDto;
import com.example.post_service.post.dto.in.PostUpdateReqDto;
import com.example.post_service.post.dto.out.ExistsPostDto;
import com.example.post_service.post.dto.out.GetPostInfoResDto;
import com.example.post_service.post.entity.Post;
import com.example.post_service.post.infrastructure.PostRepository;
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
                .mainCategoryId(post.getMainCategoryId())
                .subCategoryId(post.getSubCategoryId())
                .title(post.getTitle())
                .contents(post.getContents())
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
                postUpdateReqDto.getMainCategoryId(),
                postUpdateReqDto.getSubCategoryId()
        );
        postRepository.save(post);
    }

    @Override
    public GetPostInfoResDto getPostInfo(String postUuid) {
        return GetPostInfoResDto.from(postRepository.findByPostUuid(postUuid)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")));
    }

    @Override
    public ExistsPostDto existsPost(String postUuid) {
        return ExistsPostDto.from(postRepository.existsByPostUuid(postUuid));
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
