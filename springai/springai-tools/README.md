# Spring AI Tools

A simple  program to demonstrate use of tools when using SpringAI.

## Overview

This repository contains a basic Spring AI application demonstrating simple interaction with an LLM using Spring's ChatClient.  
It serves as a foundational project while learning Spring AI concepts through various articles and tutorials.

### Key Features
- Basic Spring AI configuration (Check application.yaml)
- Tools usage

## Purpose

This project explores:
- Use of tools in Spring AI

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

- MavenMetaDataTool
- SpringCloudAWSVersionFinderService

## Links

- [Empowering AI with Tools](https://mohankumarsagadevan.medium.com/spring-ai-101-empowering-ai-with-tools-the-tool-annotation-and-function-calling-aa4f9fe64d50)
- [Spring AI tool calling](https://docs.spring.io/spring-ai/reference/api/tools.html)
- [How LLM use Tools](https://www.linkedin.com/posts/luongnv89_mcp-model-context-protocol-sequence-diagram-share-7303100651759169538-dA8a/)
