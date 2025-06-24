package com.example.chatservice.domain.infrastructure;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class ChatMessageCustomRepositoryImpl implements ChatMessageCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Map<String, Integer> getUnreadMessageCountByChatRoom(String receiverUuid) {
        MatchOperation match = match(
                Criteria.where("receiverUuid").is(receiverUuid).and("read").is(false)
        );

        GroupOperation group = group("chatRoomUuid").count().as("count");

        Aggregation aggregation = newAggregation(match, group);

        AggregationResults<Document> results =
                mongoTemplate.aggregate(aggregation, "chat_message", Document.class);

        return results.getMappedResults().stream()
                .collect(Collectors.toMap(
                        doc -> doc.getString("_id"),
                        doc -> doc.get("count", Number.class).intValue()
                ));
    }


}
