package com.mwc.langchain4j.controller;

import com.mwc.langchain4j.aiservice.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class chatController {
//    @Autowired
//    private OpenAiChatModel model;
//
//    @RequestMapping("/chat")
//    public String chat(String question) {
//        String chat = model.chat(question);
//        return chat;
//    }
   /* @Autowired
    private ConsultantService consultantService;
    @RequestMapping("/chat")
    public String chat(String question) {
        String chat = consultantService.chat(question);
        return chat;
    }*/
    @Autowired
    private ConsultantService consultantService;
    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String memoryId, String question) {
        Flux<String> chat = consultantService.chat(memoryId,question);
        return chat;
    }
}
