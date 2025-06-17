package com.example.postreadservice.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostViewCountSyncScheduler {

    private final RedisTemplate<String, Object> redisTemplate;
    private final MongoTemplate mongoTemplate;

    /**
     * Redis에 누적된 게시글 조회수를
     * 일정 주기마다 MongoDB(PostReadModel)에 반영하는 배치 스케줄러.
     *
     * - Redis Key: aggregate:post:view:{postUuid}
     * - 주기: 60초 (fixedRate)
     */
    @Scheduled(fixedRate = 60000)
    public void syncViewCounts() {
        log.info("[View Sync] START - Redis에서 조회수 동기화 시작");
        Set<String> keys = redisTemplate.keys("aggregate:post:view:*");
        if (keys == null) {
            log.info("[View Sync] 처리할 키 없음. 종료.");
            return;
        }

        log.debug("[View Sync] 찾은 키 개수: {} | keys: {}", keys.size(), keys);

        for (String key : keys) {
            String postUuid = key.replace("aggregate:post:view:", "");
            Object value = redisTemplate.opsForValue().get(key);

            if (value == null) continue;

            long viewCount = ((Number) value).longValue();

            log.info("[View Sync] 업데이트 대상 postUuid={} | 증가된 조회수={}", postUuid, viewCount);

            Query query = new Query(Criteria.where("postUuid").is(postUuid));
            Update update = new Update().inc("viewCount", viewCount);
            mongoTemplate.updateFirst(query, update, "post_read");

            log.info("[View Sync] postUuid={} | viewCount+={}", postUuid, viewCount);
        }
        redisTemplate.delete(keys);
        log.info("[View Sync] END - 전체 Redis 키 삭제 완료 | 삭제된 키 수: {}", keys.size());
    }
}
