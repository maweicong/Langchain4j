package com.mwc.langchain4j.repository;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
@Repository
public class RedisChatMemoryStore implements ChatMemoryStore {
    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        //从Redis中获取会话消息
         String json = (String) redisTemplate.opsForValue().get(memoryId);
       // 将json转换成list
         List<ChatMessage>  list = ChatMessageDeserializer.messagesFromJson(json);
        return list;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        // 更新会话消息 到Redis中
        //1.将list转换成json
        String json = ChatMessageSerializer.messagesToJson(list);
        //2.把json保存到Redis中
        redisTemplate.opsForValue().set(memoryId.toString(), json, Duration.ofDays(1));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplate.delete(memoryId.toString());
    }
}
