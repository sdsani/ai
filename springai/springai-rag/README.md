# Spring AI RAG

A simple  program to demonstrate use of RAG with SpringAI.  
Spring AI provides out-of-the-box support for common RAG flows using the Advisor API.

## Overview

This repository contains a basic Spring AI application demonstrating simple interaction with an LLM using Spring's ChatClient.  
It serves as a foundational project while learning Spring AI concepts through various articles and tutorials.

### Key Features
- Basic Spring AI configuration (Check application.yaml)
- RAG in action

## Purpose

This project explores:
- Using RAG with Spring AI

## Getting Started

This is a work-in-progress learning project for mastering Spring AI fundamentals.

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

## Pay Attention to following in the HelloSpringAI.java


## Links

- [Demystifying RAG — Embeddings, Vector Databases, and the ETL Pipeline](https://mohankumarsagadevan.medium.com/spring-ai-101-demystifying-rag-embeddings-vector-databases-and-the-etl-pipeline-8f27ff0f88af)
- [Spring AI GitHub examples](https://github.com/spring-projects/spring-ai-examples)
- [Spring AI RAG Docs](https://docs.spring.io/spring-ai/reference/api/retrieval-augmented-generation.html)