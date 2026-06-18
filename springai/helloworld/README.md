# Spring AI Hello World

A simple hello world program to get started with Spring AI and learn how to interact with Large Language Models (LLMs).

## Overview

This repository contains a basic Spring AI application demonstrating simple interaction with an LLM using Spring's ChatClient. It serves as a foundational project while learning Spring AI concepts through various articles and tutorials.

### Key Features
- Basic Spring AI configuration (Check application.yaml)
- System messages vs user messages (HelloSpringAI.java, AIController.java)
- Dynamic prompt construction (HelloSpringAI.java)

## Purpose

This project explores:
- Basic Spring AI setup and configuration
- Creating a simple chat client application
- Interacting with LLMs using Spring AI

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

- .defaultSystem( SYSTEM_PROMPT ) sets the personality and behavior
- .user( dynamicPrompt )
- Creativity options set in application.yaml
  - Above option set's it for all interactions.
  - Another option is to set it per chat
- .entity() method in HelloSrpingAi.

## Links

- [Bride the gap between Java and AI](https://mohankumarsagadevan.medium.com/spring-ai-101-bridging-the-gap-between-java-and-generative-ai-f920af1473a1)
- [Hello World with Spring AI](https://mohankumarsagadevan.medium.com/spring-ai-101-hello-world-with-chatclient-mastering-the-fluent-api-and-prompts-97beda2cbdd7)
- [Structured Output Mapping to Java Records](https://mohankumarsagadevan.medium.com/spring-ai-101-beyond-plain-text-structured-output-mapping-to-java-records-ef26e9f08150)

## Running OLLAMA locally
- [Install docker using instructions](https://docs.docker.com/engine/install/ubuntu/)
- [Run ollama using docker](https://github.com/mythrantic/ollama-docker)
  - cd ollama-docker
  - docker compose up -d
- [Verify](http://localhost:8080)
- [Ollama Models](https://ollama.com/library)

## Other options

- [Hugging Face](https://huggingface.co/)

## Switching between OLLAMA and OpenAI
Update following files.
- application.yaml
- build.gradle