package com.example.commentservice.domain.comment.infrastructure;

import com.example.commentservice.domain.comment.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommentRepository extends MongoRepository<Comment, String>,CommentCustomRepository {
    Optional<Comment> findByCommentUuid(String commentUuid);

}
