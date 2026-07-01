# Assistant Agent using Spring AI

A simple program to demonstrate a DevOps agent 

## Overview

DevOps agent capable of assist and perform tasks

## Building Journey

### RAG and Command Generation

- Pre-trained knowledge: /src/main/resources/documents
- Ingestion: RagIngestionConfig.java 
- Search: RAGSearchService.java, RAGSearchController.java
- ChatService.java: Chat service to take user input and augment it with RAG search results to generate a command


## How to Save cost on LLM
To save cost on LLM interactions, you can:

```
Instead of using 
implementation 'org.springframework.ai:spring-ai-starter-model-openai'

use implementation 'org.springframework.ai:spring-ai-starter-model-ollama'

and run it locally.
```

## Fluent API

In Spring AI, chatClient.prompt(messageString) is a shorthand convenience method for simple, single-turn interactions, defaulting the payload as a user message.   
In contrast, chatClient.prompt().user(...) is part of the Spring AI Chat Client fluent API, letting you chain system prompts,   
advisors, and parameters for complex, multi-turn AI workflows.

- [Fluent API Deep Dive](https://dev.to/nk_sk_6f24fdd730188b284bf/understanding-fluent-api-in-spring-a-deep-dive-51lh)

## Links

- [Actual Article](https://medium.com/@kasimoluwasegun/i-built-a-cloud-aware-ai-assistant-with-spring-boot-and-react-heres-the-blueprint-ba7b07e251a8)
- [RAG Ingestion and Search](https://docs.spring.io/spring-ai/reference/api/vectordbs.html)
- [How to perform Vector Searching Using Vector Store](https://www.reddit.com/r/SpringAIDev/comments/1tlf08o/spring_ai_how_to_perform_vector_search_using/)
