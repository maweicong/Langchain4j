package com.mwc.langchain4j.aiservice;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "openAiChatModel",    //指定模型
        streamingChatModel = "openAiStreamingChatModel",
        //chatMemory="chatMemory"//会话记忆对象
        chatMemoryProvider = "chatMemoryProvider",//会话记忆提供者对象
        contentRetriever = "contentRetriever",
        tools = "reservationTool"
)
//用于聊天的方法
//@AiService
public interface ConsultantService {
    @SystemMessage(fromResource = "system.txt")
    public Flux<String> chat(@MemoryId String memoryId, @UserMessage String message);
}
