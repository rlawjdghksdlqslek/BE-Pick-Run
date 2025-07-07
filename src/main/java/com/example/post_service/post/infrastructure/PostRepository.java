package com.example.post_service.post.infrastructure;

import com.example.post_service.post.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{ 'postUuid': ?0, 'deletedStatus': false }")
    boolean existsByPostUuid(String postUuid);

    @Query("{ 'postUuid': ?0, 'deletedStatus': false }")
    Optional<Post> findByPostUuid(String postUuid);
}
