# Spring AI MCP

A simple  program to demonstrate use of mcp with SpringAI.  
Often described as a “USB-C port for AI applications,”  

An MCP server provides tools that an agent can call as part of its execution loop.  
If skills represent what an agent knows how to do, then tools represent what an agent is capable of doing.  

## Overview

This repository contains a basic Spring AI application demonstrating simple interaction with an LLM using Spring's ChatClient.  
It serves as a foundational project while learning Spring AI concepts through various articles and tutorials.

### Key Features
- Basic Spring AI configuration (Check application.yaml)
- MCP in action

## Purpose

This project explores:
- Using MCP with Spring AI

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

- 
- 

## Links

- [Creating an MCP Client](https://thetalkingapp.medium.com/spring-ai-recipe-creating-an-mcp-client-9691ddc42dfd)
- [Brave MCP Server](https://github.com/brave/brave-search-mcp-server)
- [Model Context Protocol (MCP)](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html)
- [Getting Started with Model Context Protocol (MCP)](https://docs.spring.io/spring-ai/reference/guides/getting-started-mcp.html)
- [Connect Your AI to Everything: Spring AI's MCP Boot Starters](https://spring.io/blog/2025/09/16/spring-ai-mcp-intro-blog)
- [Tavily MCP Server](https://docs.tavily.com/documentation/mcp)
- [How LLM use Tools](https://www.linkedin.com/posts/luongnv89_mcp-model-context-protocol-sequence-diagram-share-7303100651759169538-dA8a/)
- [The Advisors API — Interceptors, Logging, SafeGuard and Chat Memory](https://mohankumarsagadevan.medium.com/spring-ai-101-the-advisors-api-interceptors-logging-safeguard-and-chat-memory-c5315d3500c5)
- [Advisor API](https://docs.spring.io/spring-ai/reference/api/advisors.html)
- [Spring AI GitHub examples](https://github.com/spring-projects/spring-ai-examples)
- [Model Context Protocol Explained in 3 Levels of Difficulty](https://machinelearningmastery.com/model-context-protocol-explained-in-3-levels-of-difficulty/)
- [MCP Security Best Practices](https://modelcontextprotocol.io/docs/tutorials/security/security_best_practices)
- [Unlocking the Model Context Protocol (@Deprecated)](https://mohankumarsagadevan.medium.com/spring-ai-101-unlocking-the-model-context-protocol-mcp-standardizing-ai-tools-8369e498e273)