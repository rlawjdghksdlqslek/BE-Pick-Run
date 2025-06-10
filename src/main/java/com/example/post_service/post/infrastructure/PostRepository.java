package com.example.post_service.post.infrastructure;

import com.example.post_service.post.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    boolean existsByPostUuid(String postUuid);
}
