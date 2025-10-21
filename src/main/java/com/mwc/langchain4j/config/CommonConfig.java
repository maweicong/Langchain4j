package com.mwc.langchain4j.config;

import com.mwc.langchain4j.aiservice.ConsultantService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfig {
    @Autowired
    private OpenAiChatModel model;
    @Autowired
    private ChatMemoryStore redisChatMemoryStore;
    @Autowired
    private EmbeddingModel embeddingModel;
   /* @Bean
    public ConsultantService consultantService() {
        ConsultantService consultantService = AiServices.builder(ConsultantService.class)
                .chatModel(model)
                .build();
        return consultantService;
    }*/
    //构建会话记忆对象
    @Bean
    public ChatMemory chatMemory() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        return chatMemory;
    }
    //构建ChatMemoryProvider对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .chatMemoryStore(redisChatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;
    }
    //构建向量数据库操作对象
    @Bean
    public EmbeddingStore store() {
        //加载文档进内存
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content",new ApachePdfBoxDocumentParser());
        //创建向量数据库操作对象
        InMemoryEmbeddingStore store =new InMemoryEmbeddingStore();
        //构建文档分割器
        DocumentSplitter ds = DocumentSplitters.recursive(500,100);
        //构建一个EmbeddingStoreIngestor对象，完成数据切割，向量化，存储
        EmbeddingStoreIngestor ingestor =  EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .documentSplitter(ds)
                .embeddingModel(embeddingModel)
                .build();

        ingestor.ingest(documents);
        return store;
    }

    //构建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore store) {
       return   EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .minScore(0.7)
                .maxResults(3)
                .embeddingModel(embeddingModel)
                .build();
            }
}
