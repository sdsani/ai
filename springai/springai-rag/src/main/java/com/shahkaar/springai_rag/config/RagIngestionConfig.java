package com.shahkaar.springai_rag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

// https://thetalkingapp.medium.com/spring-ai-recipe-essential-rag-c1798773668b
@Configuration
@Slf4j
public class RagIngestionConfig {

    @Bean
    SimpleVectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean
    ApplicationRunner load(VectorStore vectorStore) {

        return _ -> {
            loadResource(vectorStore, new ClassPathResource("shahkaar-office-hour-policy.md"));
            loadResource(vectorStore, new ClassPathResource("sahaf-mills-office-hour-plolicy.md"));
        };
    }

    private void loadResource(VectorStore vectorStore, ClassPathResource resource) {
        log.info("Loading document from {}.", resource);
        var reader = new TikaDocumentReader(resource);
        var splitter = TokenTextSplitter.builder().build();
        vectorStore.accept(
                splitter.apply(
                        reader.get()));
        log.info("Document loading complete.");
    }
}