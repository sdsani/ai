package com.shahkaar.helloworld.controller;

import com.shahkaar.helloworld.service.HelloSpringAI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AIController {

    private final HelloSpringAI helloSpringAI;

    // http://localhost:8080/ai/helloworld
    @GetMapping("/helloworld")
    public String helloSpring() {

        log.info("====> Calling ai/tools");
        return helloSpringAI.chat("1234", "Tell me a joke.");
    }
}
