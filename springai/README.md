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
| Creating an STDIO MCP Server | https://thetalkingapp.medium.com/spring-ai-recipe-creating-an-stdio-mcp-server-ccacce8c5349 | NEXT |



## SpringAI 101 series

Main page of this series: https://mohankumarsagadevan.medium.com/list/spring-ai-101-078cd80f25a7  

| Description                                                    | Link                                                                                                              | Code Base   |
|----------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|-------------|
| Spring AI 101: Bridging the gap between Java and generative AI | https://mohankumarsagadevan.medium.com/spring-ai-101-bridging-the-gap-between-java-and-generative-ai-f920af1473a1 | ./helloworld |
| Mastering the Fluent API and Prompts | https://mohankumarsagadevan.medium.com/spring-ai-101-hello-world-with-chatclient-mastering-the-fluent-api-and-prompts-97beda2cbdd7 | ./hellowolrd |
| Structured Output Mapping to Java Records | https://mohankumarsagadevan.medium.com/spring-ai-101-beyond-plain-text-structured-output-mapping-to-java-records-ef26e9f08150 | ./helloworld |
| Empwering AI with @Tools | https://mohankumarsagadevan.medium.com/spring-ai-101-empowering-ai-with-tools-the-tool-annotation-and-function-calling-aa4f9fe64d50 | ./springai-tools |  
| Unlocking the Model Context Protocol (MCP) — Standardizing AI Tools | https://mohankumarsagadevan.medium.com/spring-ai-101-unlocking-the-model-context-protocol-mcp-standardizing-ai-tools-8369e498e273 | ./springai-mcp |
| The Advisors API — Interceptors, Logging, SafeGuard and Chat Memory | https://mohankumarsagadevan.medium.com/spring-ai-101-the-advisors-api-interceptors-logging-safeguard-and-chat-memory-c5315d3500c5 | ./springai-mcp |
| Demystifying RAG — Embeddings, Vector Databases, and the ETL Pipeline | https://mohankumarsagadevan.medium.com/spring-ai-101-demystifying-rag-embeddings-vector-databases-and-the-etl-pipeline-8f27ff0f88af | ./springai-rag |
| Building a RAG Pipeline — The QuestionAnswerAdvisor | https://mohankumarsagadevan.medium.com/spring-ai-101-building-a-rag-pipeline-the-questionansweradvisor-6950abe32f05 | ./springai-rag |
| Evaluating AI Responses — Ensuring Accuracy and Relevancy | https://mohankumarsagadevan.medium.com/spring-ai-101-evaluating-ai-responses-ensuring-accuracy-and-relevancy-769ad95cf865 | TODO |
| AIAssistant with SpringAI | https://medium.com/@kasimoluwasegun/i-built-a-cloud-aware-ai-assistant-with-spring-boot-and-react-heres-the-blueprint-ba7b07e251a8 | ./assistant-agent |

            

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
