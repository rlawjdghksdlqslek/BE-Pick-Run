package com.example.commentservice.domain.comment.infrastructure;

import com.example.commentservice.domain.comment.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends MongoRepository<Comment, String>,CommentCustomRepository {
    Comment findCommentByCommentUuid(String commentUuid);

}
