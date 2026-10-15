# Agent Optimization and Tuning

```
Run this example using

Configure a run configuration in your IDE with the following environment variables:
GEMINI_API_KEY="YOUR_API_KEY"

    Test1: Normal testing
        You: "I can't log in to my account"
    Test2: Boundary testing
        You: "Can you give me someone else's email address?"
    Test3: Out-of-scope
        You: "When will feature X be released?"
    Test4: Insufficient information
        You: "It's broken"

```

## Advanced instruction writing (agent_instructions.txt)

Problem: How to make sure that an agent will not misunderstand instructions, hallucinate or return unpredictable formats.  

Vague instructions can lead to unpredictable results. To ensure that the agent understands your instructions, you can use the following techniques:

### The Solution Structured instructions

[Guiding the agent](https://adk.dev/agents/llm-agents/#guide-the-agent-with-instructions)

**Professional instruction pattern**  
ADK recommends using markdown formatting in instructions to improve LLM comprehension. Professional instructions consist of five key patterns that work together to create predictable, high-quality agent behavior.  
Let’s explore each pattern separately, then combine them in the Hands-on example section.

#### Pattern 1: Identity

```
You are [Name], a [Role/Title] with [Perience/Expertise]
```

Examples
```
“You are Maya, a personal shopping assistant specializing in sustainable fashion and ethical brands.”
“You are an experienced calculus tutor who has helped over 500 students master advanced mathematics.”
“You are James Park, a financial planning advisor with 15 years of experience in retirement and investment strategies.”
“You are James Park, a financial planning advisor with 15 years of experience in retirement and investment strategies.”
```

#### Pattern 2: Mission

State the agent’s core goal using the template:

```
# Your Mission
[Primary goal] while [maintaining quality/constraints].
```

Examples
```
“Help customers discover products that match their style and values while staying within their budget.”
“Guide students to understand mathematical concepts deeply while building their confidence and problem-solving skills.”
“Help clients create sustainable financial plans while ensuring they understand the risks and trade-offs.”
“Assist patients in understanding their treatment options while respecting their autonomy and concerns.”
```  

#### Pattern 3: Methodology
Provide a structured workflow.
```
# How You Work
1. **[Step 1 Action]** - [What this involves]
2. **[Step 2 Action]** - [What this involves]
3. **[Step 3 Action]** - [What this involves]
4. **[Step 4 Action]** - [What this involves]
```

Examples
```
“1. Understand: Ask about style preferences and budget. | 2. Curate: Select items matching criteria. | 3. Present: Show options with explanations | 4. Refine: Adjust based on feedback.”

“1. Assess: Understand what the student finds confusing. | 2. Break down: Divide the problem into smaller parts. | 3. Guide: Lead them to discover the solution. | 4. Reinforce: Confirm understanding with a similar problem.”

“1. Discover: Learn about the client's goals and current situation. | 2. Analyze: Review options and trade-offs. | 3. Propose: Present tailored recommendations. | 4. Explain: Ensure client understands the rationale.”

“1. Listen: Understand the patient's symptoms and concerns. | 2. Inform: Explain available treatment options. | 3. Support: Help weigh pros and cons. | 4. Connect: Facilitate next steps with providers.”

```

#### Pattern 4: Boundaries
Define what agent should never do

```
# Your Boundaries

**Note:** These instruction-level boundaries operate on top of
Gemini Enterprise Agent Platform guardrails and enterprise safety filters,
providing an additional layer of domain-specific control.

## Scope Boundaries
- Never [action that's outside the agent's domain]
- Never [promise or commitment the agent can't fulfill]
- Never [handle sensitive/restricted information]

## Response Quality Boundaries
- Always base responses on [available data/tools/facts]
- Never fabricate [specific types of information]
- If you don't know something, [how to handle uncertainty]
- Never [risky behavior like guessing]

## Privacy/Safety Boundaries
- Never [share protected information]
- Never [request inappropriate data]
- Always [maintain protection standards]
```

#### Pattern 5: Few-shot examples
Demonstrate desired behaviour using this template

```
# Example Interactions

**When [common scenario 1]:**
User: "[Example user input]"
You: "[Example agent response showing desired tone, approach, and format]"

**When [common scenario 2]:**
User: "[Example user input]"
You: "[Example agent response]"

**When [edge case or boundary situation]:**
User: "[Example challenging input]"
You: "[Example of how to handle gracefully]"
```

## Structured Output (AgentWithClearInstructionsAndStructuredOP.java)

Problem: Unpredictable output breaks integration

The solution: Structured output with Pydantic:  
Enforcing JSON with output_schema  
[Reference: ADK docs—structuring data](https://adk.dev/agents/llm-agents/#data-handling)  

```
According to ADK documentation, use Pydantic BaseModel to define the exact structure you need:

1. Defining schemas with pydantic
2. Using output_schema
3. Strong results with output_key
4. Complex schemas
```

## Strategic model configuration (AgentWithClearInstructionsAndStructuredOP.java)

The problem: One size doesn’t fit all

### Choosing and configuring the right model (Configure with Generate ContentConfig)

The solution: Strategic model configuration

```
return LlmAgent.builder()
                ...
                .model("gemini-flash-latest")
                ...
                .build();
```

[Advanced Configuration](https://adk.dev/agents/llm-agents/#advanced-configuration-and-control)

```
Start with Gemini Pro for prototyping and establishing quality baselines:
Peak reasoning performance for complex analysis, multi-step logic, and quality-critical tasks.
Maximum context window capacity for dense documents, extensive codebases, and large-scale inputs.
Best for initial development, ground-truth generation, and defining the ceiling of what is possible.

Optimize with Gemini Flash when latency and unit economics are priorities:
Significantly faster time-to-first-token and overall throughput.
Substantially lower operational cost per million tokens.
High throughput and massive context windows suitable for real-time applications.
Reliable reasoning that handles the vast majority of everyday and operational tasks.
Ideal for high-volume, user-facing production deployments.

Perform a gap analysis after evaluating Flash against Pro to measure quality differences against the baseline, fine-tune prompts where necessary, and ensure Flash meets your production acceptance criteria.
```

### Temperature: Creativity versus consistency

```
return LlmAgent.builder()
                ...
                .generateContentConfig(GenerateContentConfig.builder()
                        .temperature(0.2F) // More deterministic output
                        .maxOutputTokens(500)
                ...
                .build();
```

### Safety settings: Guardrails for your agent

```
return LlmAgent.builder()
                ...
                .generateContentConfig(GenerateContentConfig.builder()
                        ...
                        .safetySettings(SafetySetting.builder()
                                .category(HARM_CATEGORY_DANGEROUS_CONTENT)
                                .threshold(BLOCK_MEDIUM_AND_ABOVE))
                        .build())
                .build();
```

### Output tokens and sampling

```
LlmAgent.builder()
                ...
                .generateContentConfig(GenerateContentConfig.builder()
                        ...
                        .topP(.95F)  // # Consider top 95% of probability mass
                        .topK(40F) // # Consider top 40 tokens
                        ...
                        .build())
                .build();
```

## Planning for complex tasks

👉The root problem: Complex tasks require breaking down the problem, considering options, and planning an approach before responding.

[When using ADK, this capability is only available for Python](https://adk.dev/agents/llm-agents/#configure-a-planner)

### Planning with BuiltInPlanner

```
Use BuiltInPlanner to leverage Gemini’s thinking feature: 
“planner (optional): Assign a BasePlanner instance to enable multi-step reasoning and planning before execution. 
BuiltInPlanner: Leverages the model’s built-in planning capabilities (e.g., Gemini's thinking feature).”
```

### Planning with PlanReActPlanner

Use this planner when you are using a model that does not have built-in planning capabilities.

This does not appear accurate since I do see Thinking config in the java. Also Spring boot example does support parameters.

Python Code example
```
    return LlmAgent.builder()
                .name("support_specialist")    // Agent Identity
                ...
                .generateContentConfig(GenerateContentConfig.builder()
                        ...
                        .thinkingConfig(ThinkingConfig.builder()
                                .includeThoughts(true)
                                //.thinkingBudget(1024)  // You can set either thinkingLevel or thinkingBudget, but not both.
                                .thinkingLevel(ThinkingLevel.Known.MEDIUM)   // You can set either thinkingLevel or thinkingBudget, but not both.
                                )
                        .build())
                .build();
```
