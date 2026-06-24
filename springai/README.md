# SpringAI Learning Journey

I am learning SpringAI. In this journey, I will read and write examples that will live under the `springai` folder.

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






Building a RAG Pipeline — The QuestionAnswerAdvisor: https://mohankumarsagadevan.medium.com/spring-ai-101-building-a-rag-pipeline-the-questionansweradvisor-6950abe32f05

Evaluating AI Responses — Ensuring Accuracy and Relevancy: https://mohankumarsagadevan.medium.com/spring-ai-101-evaluating-ai-responses-ensuring-accuracy-and-relevancy-769ad95cf865

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
