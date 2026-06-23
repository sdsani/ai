package com.shahkaar.springai_tools.controller;

import com.shahkaar.springai_tools.service.SpringCloudAWSVersionFinderService;
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

    private final SpringCloudAWSVersionFinderService springCloudAWSVersionFinderService;

    // http://localhost:8081/ai/tools
    @GetMapping("/tools")
    public String toolsExample() {
        log.info("====> Calling ai/tools");
        return springCloudAWSVersionFinderService.chat("1234", "io.awspring.cloud", "spring-cloud-aws-dependencies");
    }
}
