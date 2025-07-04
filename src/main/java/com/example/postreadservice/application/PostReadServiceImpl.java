package com.example.postreadservice.application;

import com.example.postreadservice.common.exception.BaseException;
import com.example.postreadservice.common.response.BaseResponseStatus;
import com.example.postreadservice.dto.in.PostReadModelReqDto;
import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.dto.out.PostSummaryResDto;
import com.example.postreadservice.entity.PostReadModel;
import com.example.postreadservice.entity.PostSortType;
import com.example.postreadservice.infrastructure.PostReadRepository;
import com.example.postreadservice.kafka.event.PostCreatedEvent;
import com.example.postreadservice.kafka.event.PostUpdatedEvent;
import com.example.postreadservice.kafka.producer.PostViewKafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostReadServiceImpl implements PostReadService {

    private final PostReadRepository postReadRepository;
    private final PostViewKafkaProducer postViewKafkaProducer;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public void createPostRead(PostCreatedEvent postCreatedEvent) {
        postReadRepository.save(PostReadModelReqDto.from(postCreatedEvent));
    }

    @Transactional
    public void updatePostRead(PostUpdatedEvent postUpdatedEvent) {
        Optional<PostReadModel> existingPostReadModel = postReadRepository.findByPostUuid(postUpdatedEvent.getPostUuid());
        PostReadModel postReadModel = existingPostReadModel.get();
        postReadModel.updateFromEvent(postUpdatedEvent);
        postReadRepository.save(postReadModel);
    }

    @Override
    public PostReadModelResDto getPostRead(String postUuid, String memberUuid, HttpServletRequest request) {
        PostReadModel postReadModel = postReadRepository.findByPostUuid(postUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_NOT_FOUND));

        String redisKey;

        if (memberUuid != null && !memberUuid.isBlank()) {
            redisKey = "view:member:" + memberUuid + ":post:" + postUuid;
        } else {
            String ip = request.getRemoteAddr();
            String userAgent = Optional.ofNullable(request.getHeader("User-Agent")).orElse("unknown");
            String hash = DigestUtils.md5DigestAsHex((ip + userAgent).getBytes(StandardCharsets.UTF_8));
            redisKey = "view:anon:" + hash + ":post:" + postUuid;
        }

        log.info("Redis Key : {}", redisKey);

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            redisTemplate.opsForValue().set(redisKey, "1", Duration.ofMinutes(10));
            log.info("Redis에 조회 기록 저장: {}", redisKey);

            String countKey = "aggregate:post:view:" + postUuid;
            redisTemplate.opsForValue().increment(countKey);
            log.info("조회수 카운터 증가: redisKey={} → {}", countKey, postUuid);

        } else {
            log.info("중복 조회 감지됨 - redisKey={}", redisKey);
        }

        return PostReadModelResDto.from(postReadModel);
    }

    @Override
    public PostListPageResponseDto getPostBySort(
            Long mainCategoryId,
            Long subCategoryId,
            int page,
            int size,
            PostSortType postSortType
    ) {
        Pageable pageable = PageRequest.of(page, size, postSortType.getSort());

        Page<PostReadModel> resultPage = postReadRepository.findByDynamicCategory(
                mainCategoryId, subCategoryId, pageable);

        List<PostSummaryResDto> posts = resultPage.getContent().stream()
                .map(PostSummaryResDto::from)
                .toList();

        return new PostListPageResponseDto(
                posts,
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.hasNext(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages()
        );
    }

}
