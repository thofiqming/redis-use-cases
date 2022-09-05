package com.thofiq.redisusecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisMessagePublisher implements MessagePublisher {
    @Autowired
    private RedisTemplate<String, AppData> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public RedisMessagePublisher() {
    }

    public RedisMessagePublisher(
            RedisTemplate<String, AppData> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(AppData message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}
