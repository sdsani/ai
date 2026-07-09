# SpringAI Learning Journey

I am learning SpringAI. In this journey, I will read and write examples that will live under the `springai` folder.

## Spring AI Recipies

| Description                                                    | Link                                                                                                              | Code Base   |
|----------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|-------------|
| Recipies Main Page | https://thetalkingapp.medium.com/ | N/A |
| Recipies GitHubPage | https://github.com/habuma/spring-ai-recipes  | N/A |
| Agent Skill VS MCP | https://thetalkingapp.medium.com/agent-skills-vs-mcp-12aad7f48476 | N/A |
| Test based Chat Loop | https://thetalkingapp.medium.com/spring-ai-recipe-building-a-text-based-chat-loop-around-chatclient-444573e4106c | ./text-based-chat-loop |
| Composing ChatClient Behavior | https://thetalkingapp.medium.com/spring-ai-recipe-composing-chatclient-behavior-5f643e727781 | ./springai-tools |
| Logging LLM Requests and Responses | https://thetalkingapp.medium.com/spring-ai-recipe-logging-llm-requests-and-responses-c1adfba50082 | ./springai-tools |
| Asking Questions to the User | https://thetalkingapp.medium.com/spring-ai-recipe-asking-questions-to-the-user-99ec1a0e2394 | ./text-based-chat-loop |
| Agentic Planning with TodoWriteTool | https://thetalkingapp.medium.com/spring-ai-recipe-agentic-planning-with-todowritetool-5c415ef5a816 | ./text-based-chat-loop |
| Guiding Agent Behavior with Skills | https://thetalkingapp.medium.com/spring-ai-recipe-guiding-agent-behavior-with-skills-739d1b29f3c2 | NONE |
| Reusing Agent Behavior with SkillsJars | https://thetalkingapp.medium.com/spring-ai-recipe-reusing-agent-behavior-with-skillsjars-61636549fa79 | None |
| Enabling Agent-to-Agent Communication with A2A | https://thetalkingapp.medium.com/spring-ai-recipe-enabling-agent-to-agent-communication-with-a2a-2cdd97794a6f | None |
| Invoking A2A Sub-Agents with TaskTool | https://thetalkingapp.medium.com/spring-ai-recipe-invoking-a2a-sub-agents-with-tasktool-e4f1a4b92b5f | None |
| Enabling Long-Term Memory | https://thetalkingapp.medium.com/spring-ai-recipe-enabling-long-term-memory-49186aa60cb0 | None |
| Creating an STDIO MCP Server | https://thetalkingapp.medium.com/spring-ai-recipe-creating-an-stdio-mcp-server-ccacce8c5349 | NONE |
| Streamable HTTP MCP Server | https://thetalkingapp.medium.com/spring-ai-recipe-streamable-http-mcp-server-e9c70590b872 | NONE |
| Creating an MCP Client | https://thetalkingapp.medium.com/spring-ai-recipe-creating-an-mcp-client-9691ddc42dfd | ./springai-mcp |
| Building a Graph-Based Agentic Workflow | https://thetalkingapp.medium.com/spring-ai-recipe-building-a-graph-based-agentic-workflow-becfae64170a | ./graph-using-alibaba |
| Adding Human-in-the-Loop to a Graph-based Agentic Workflow | https://thetalkingapp.medium.com/spring-ai-recipe-adding-human-in-the-loop-to-a-graph-based-agentic-workflow-4a826cf89902 | Next |
            

## How to Save Cost (Running OLLAMA locally)

- [Install docker using instructions](https://docs.docker.com/engine/install/ubuntu/)
- [Run ollama using docker](https://github.com/mythrantic/ollama-docker)
    - cd ollama-docker
    - sudo docker compose -f ./docker-compose.yml up -d
- [Verify](http://localhost:8080)
- [Ollama Models](https://ollama.com/library)

## Other options

- [Hugging Face](https://huggingface.co/)

## Switching between OLLAMA and OpenAI
Update following files.
- application.yaml
- build.gradle
