package com.shahkaar.assitant_agent.controller;

import com.shahkaar.assitant_agent.service.RAGSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class RAGSearchController {

    private final RAGSearchService searchService;

    // http://localhost:8081/ai/searchrag
    @GetMapping("/searchrag")
    public String ragSearch() {

        // Following queries work
        //String query = "AWS S3 Basics";
        //String query = "aws-s3-basics";
        //String query = "AWS S3 Knowledge base";

        String query = "AWS CLI Getting Started";
        log.info("====> Calling Search RAG, query: {}", query);
        return searchService.searchDocuments(query);
    }

}
