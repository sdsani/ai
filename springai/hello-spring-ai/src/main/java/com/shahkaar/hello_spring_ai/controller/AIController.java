package com.shahkaar.hello_spring_ai.controller;

import com.shahkaar.hello_spring_ai.service.SpringCloudAWSVersionFinder;
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

    private final SpringCloudAWSVersionFinder springCloudAWSVersionFinder;

    // http://localhost:8080/ai/tools
    @GetMapping("/tools")
    public String toolsExample() {

        log.info("====> Calling ai/tools");
        return springCloudAWSVersionFinder.findLatestVersion("io.awspring.cloud", "spring-cloud-aws-dependencies");
    }
}
