package com.shahkaar;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.RunConfig;
import com.google.adk.events.Event;
import com.google.adk.runner.Runner;
import com.google.adk.sessions.InMemorySessionService;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import com.shahkaar.agents.AgentWithSate;
import io.reactivex.rxjava3.core.Flowable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

// Following has been moved to application.yaml and AdkConfiguration.java
@Deprecated
public class SateManagementExample {

    //private static final String appName = "state-management-example";
   // private static final String userId = "user1";
   // private static final String sessionId = "session1";

    // Have document equivalent code from springboot and commented each line out
    static void main(String[] args) {

        // AdkConfiguration.rootAgent()
        //BaseAgent stateFullAgent = AgentWithSate.ROOT_AGENT;

        // AdkConfiguration.sessionService()
        //InMemorySessionService sessionService = new InMemorySessionService();

        // AdkConfiguration.runConfig()
        //RunConfig runConfig = RunConfig.builder().build();

        // AdkConfiguration.runner()
        //Runner runner = Runner.builder()
        //        .agent(stateFullAgent)
        //        .appName(appName)
        //        .sessionService(sessionService)
        //        .build();

        // Following is injected using application properties.
        // Initialize session state
        //Map<String, Object> initialState = new HashMap<>();
        //initialState.put("agentName", "Alex Channa");

        // AdkChatService.createSession() equivalent code
        //Session session =
        //        sessionService.createSession(appName, userId, initialState, sessionId).blockingGet();
        //System.out.println("Initial state: " + session.state().entrySet());


        // AdkChatService.sendMessage() equivalent code
        //
        //try (Scanner scanner = new Scanner(System.in, UTF_8)) {

       //     while (true) {
       //         System.out.print("\nYou > ");
        //        String userInput = scanner.nextLine();
        //        if ("quit".equalsIgnoreCase(userInput)) {
        //            break;
        //        }

        //        Content userMsg = Content.fromParts(Part.fromText(userInput));
        //        Flowable<Event> events = runner.runAsync(session.userId(), session.id(), userMsg, runConfig);

        //        System.out.print("\nAgent > ");
        //        events.blockingForEach(event -> {
        //            if (event.finalResponse()) {
        //                System.out.println(event.stringifyContent());
        //                session.state().put(System.currentTimeMillis() + "", event.stringifyContent());
        //            }
        //        });
        //    }
        //    System.err.println("============================================================================================");
        //    for (Map.Entry<String, Object> entry : session.state().entrySet()) {
        //        System.err.println(entry.getKey() + " : " + entry.getValue());
        //    }
        //}
    }
}
