package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.CompileConfig;
import com.alibaba.cloud.ai.graph.CompiledGraph;
import com.alibaba.cloud.ai.graph.KeyStrategy;
import com.alibaba.cloud.ai.graph.StateGraph;
import com.alibaba.cloud.ai.graph.exception.GraphStateException;
import com.alibaba.cloud.ai.graph.state.strategy.ReplaceStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static com.alibaba.cloud.ai.graph.StateGraph.END;
import static com.alibaba.cloud.ai.graph.StateGraph.START;
import static com.alibaba.cloud.ai.graph.action.AsyncEdgeAction.edge_async;
import static com.alibaba.cloud.ai.graph.action.AsyncNodeAction.node_async;

@Configuration
@Slf4j
public class SupportGraphConfiguration {

    static final String CLASSIFY = "classify";
    static final String BILLING = "billing";
    static final String TECHNICAL = "technical";
    static final String HUMAN_CLASSIFY = "human-classify"; // <-- NEW

    @Bean
    CompiledGraph supportGraph(ChatClient chatClient) throws GraphStateException {

        return new StateGraph("support-triage", this::stateStrategies)
                // Nodes
                .addNode(CLASSIFY,
                        node_async(new ClassifySupportRequestNode(chatClient)))
                .addNode(HUMAN_CLASSIFY,
                        node_async(new HumanClassificationNode())) // <-- NEW
                .addNode(BILLING,
                        node_async(new BillingSupportNode(chatClient)))
                .addNode(TECHNICAL,
                        node_async(new TechnicalSupportNode(chatClient)))
                // Edges
                .addEdge(START, CLASSIFY)
                .addConditionalEdges(
                        CLASSIFY,
                        edge_async(state -> {

                            String category = state.value("category", "unknown");
                            return switch (category) {
                                case BILLING -> BILLING;
                                case TECHNICAL -> TECHNICAL;
                                default -> HUMAN_CLASSIFY;
                            };
                        }),
                        Map.of(
                                BILLING, BILLING,
                                TECHNICAL, TECHNICAL,
                                HUMAN_CLASSIFY, HUMAN_CLASSIFY
                        ))
                .addEdge(BILLING, END)
                .addEdge(TECHNICAL, END)
                .addConditionalEdges(
                        HUMAN_CLASSIFY,
                        edge_async(state ->
                                state.value("category", TECHNICAL)),
                        Map.of(
                                BILLING, BILLING,
                                TECHNICAL, TECHNICAL
                        ))
                .compile(CompileConfig.builder()
                        .interruptBefore(HUMAN_CLASSIFY)
                        .build());
    }

    private Map<String, KeyStrategy> stateStrategies() {
        return Map.of(
                "user_question", new ReplaceStrategy(),
                "category", new ReplaceStrategy(),
                "response", new ReplaceStrategy()
        );
    }
}
