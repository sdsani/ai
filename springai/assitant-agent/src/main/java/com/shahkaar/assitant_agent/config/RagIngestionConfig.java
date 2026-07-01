package com.shahkaar.assitant_agent.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@Slf4j
public class RagIngestionConfig {

    @Value("classpath:/documents/*.md")
    private Resource[] documentResources;

    @Bean
    SimpleVectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean
    ApplicationRunner load(VectorStore vectorStore) {

        return _ -> {
            for (Resource resource: documentResources) {
                loadResource(vectorStore, resource);
            }
            log.info("Documents ingestion complete.");
        };
    }

    private void loadResource(VectorStore vectorStore, Resource resource) {
        log.info("Load document started: {}", resource.getFilename());
        var reader = new TikaDocumentReader(resource);
        var splitter = TokenTextSplitter.builder().build();
        vectorStore.accept(
                splitter.apply(
                        reader.get()
                        )
                );
        log.info("Loading document finished: {}", resource.getFilename());
    }
}