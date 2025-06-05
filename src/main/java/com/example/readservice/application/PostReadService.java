package com.example.readservice.application;

import com.example.readservice.kafka.event.PostCreatedEvent;

public interface PostReadService{
    void createPostRead(PostCreatedEvent postCreatedEvent);

}
