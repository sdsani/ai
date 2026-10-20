package com.shahkaar.service;

import com.google.adk.agents.RunConfig;
import com.google.adk.events.Event;
import com.google.adk.runner.Runner;
import com.google.adk.sessions.InMemorySessionService;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;

import com.shahkaar.config.AdkProperties;
import io.reactivex.rxjava3.core.Flowable;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AdkChatService {

    private final Runner runner;
    private final InMemorySessionService sessionService;
    private final RunConfig runConfig;
    private final AdkProperties properties;

    public AdkChatService(
            Runner runner,
            InMemorySessionService sessionService,
            RunConfig runConfig,
            AdkProperties properties) {
        this.runner = runner;
        this.sessionService = sessionService;
        this.runConfig = runConfig;
        this.properties = properties;
    }

    public String createSession() {

        Session session = sessionService.createSession(
                properties.getAppName(),
                properties.getUserId(),
                new HashMap<>(properties.getInitialState()),
                null
        ).blockingGet();

        return session.id();
    }

    public String sendMessage(String sessionId, String message) {

        String response = "";

        Content userMsg = Content.fromParts(Part.fromText(message));

        Flowable<Event> events = runner.runAsync(
                properties.getUserId(),
                sessionId,
                userMsg,
                runConfig
        );

        for (Event event : events.blockingIterable()) {
            if (event.finalResponse()) {
                response = event.stringifyContent();
            }
        }

        // Update state after the response has been generated.
        Session session = sessionService.getSession(
                properties.getAppName(),
                properties.getUserId(),
                sessionId,
                Optional.empty()
        ).blockingGet();

        if (session == null) {
            throw new IllegalStateException(
                    "Session not found: " + sessionId);
        }

        session.state().put(
                String.valueOf(System.currentTimeMillis()),
                response
        );

        return response;
    }
}
