package com.example.commentservice.domain.comment.infrastructure;

import com.example.commentservice.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Comment> findCommentByPostUuid(String postUuid, Pageable pageable) {
        Query baseQuery = new Query(Criteria.where("postUuid").is(postUuid));

        long total = mongoTemplate.count(baseQuery, Comment.class);
        List<Comment> comments = mongoTemplate.find(baseQuery.with(pageable), Comment.class);
        return new PageImpl<>(comments, pageable, total);
    }
}
