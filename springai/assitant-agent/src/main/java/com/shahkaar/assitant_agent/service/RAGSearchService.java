package com.shahkaar.assitant_agent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStoreRetriever;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RAGSearchService {

    private final VectorStoreRetriever retriever;

    public List<Document> searchRelevantDocuments(String question) {

        SearchRequest request = SearchRequest.builder().
                query(question).
                topK(1).
                similarityThreshold(0.7).
                build();

        return retriever.similaritySearch(request);
    }

    public String searchRelevantDocumentsAsString(String question) {

        List<Document> relevantDocs = searchRelevantDocuments(question);

        log.info("RAG search returned {} relevant documents.", relevantDocs.size());
        for (Document doc:relevantDocs) {
            log.info("Document id: {}, Name: {}", doc.getId(), doc.getMetadata().get("source"));
        }

        return relevantDocs.stream().map(Document::getText).
                collect(Collectors.joining("\n\n"));
    }
}
