package com.shahkaar.assitant_agent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStoreRetriever;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RAGSearchService {

    private final VectorStoreRetriever retriever;

    public List<Document> searchDocuments(String question) {

        SearchRequest request = SearchRequest.builder().
                query(question).
                topK(1).
                similarityThreshold(0.7).
                build();

        return retriever.similaritySearch(request);
    }
}
