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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Comment> findCommentByPostUuid(String postUuid, Pageable pageable) {
        Query baseQuery = new Query(Criteria.where("postUuid").is(postUuid)
                .and("deleted_status").is(false));

        long total = mongoTemplate.count(baseQuery, Comment.class);
        List<Comment> comments = mongoTemplate.find(baseQuery.with(pageable), Comment.class);
        return new PageImpl<>(comments, pageable, total);
    }

    @Override
    public Optional<Comment> findNotDeletedByCommentUuid(String commentUuid) {
        Query query = new Query(Criteria.where("commentUuid").is(commentUuid)
                .and("deleted_status").is(false));
        return Optional.ofNullable(mongoTemplate.findOne(query, Comment.class));
    }
}
