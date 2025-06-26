package com.example.commentservice.domain.comment.infrastructure;

import com.example.commentservice.domain.comment.entity.CommentLike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends MongoRepository<CommentLike, String> {
    boolean existsByCommentUuidAndMemberUuid(String commentUuid, String memberUuid);

    Optional<CommentLike> findByCommentUuidAndMemberUuid(String commentUuid, String memberUuid);

}
