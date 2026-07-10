package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class HumanClassificationNode implements NodeAction {

    @Override
    public Map<String, Object> apply(OverAllState state) {
        log.info("Human classification required. Please classify the support request manually.");
        return Map.of();
    }

}