package com.example.commentservice.domain.comment.infrastructure;

import com.example.commentservice.domain.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentCustomRepository {
    Page<Comment> findCommentByPostUuid(String postUuid, Pageable pageable);

    Optional<Comment> findNotDeletedByCommentUuid(String commentUuid);
}
