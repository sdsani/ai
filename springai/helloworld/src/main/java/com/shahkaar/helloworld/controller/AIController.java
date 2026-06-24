package com.shahkaar.helloworld.controller;

import com.shahkaar.helloworld.model.dto.TopDestinationsResponse;
import com.shahkaar.helloworld.service.HelloSpringAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AIController {

    private final HelloSpringAIService helloSpringAIService;

    // http://localhost:8081/ai/helloworld
    @GetMapping("/helloworld")
    public TopDestinationsResponse helloSpring() {

        log.info("====> Calling helloSpringAI.chat");
        return helloSpringAIService.chat("1234", "Lahore");
    }

    // http://localhost:8081/ai/asynchelloworld
    // Stream does not support transformation into a custom object. It can only return the content as string.
    @GetMapping("/asynchelloworld")
    public Flux<String> asyncHelloSpring() {
        log.info("====> Calling helloSpringAI.aSyncChat");
        return helloSpringAIService.aSyncChat("1234", "Lahore");
    }
}
