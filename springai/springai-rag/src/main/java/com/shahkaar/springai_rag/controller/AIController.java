package com.shahkaar.springai_rag.controller;

import com.shahkaar.springai_rag.model.dto.CompanyOfficeHours;
import com.shahkaar.springai_rag.service.OfficeHourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AIController {

    private final OfficeHourService officeHourService;

    // http://localhost:8081/ai/bravesearch
    @PostMapping("/office-hours")
    public CompanyOfficeHours braveSearch(@RequestBody Map<String, String> request) {

        String prompt = request.get("prompt");
        log.info("====> Calling ai/office-hours, Prompt: {}", prompt);
        return officeHourService.chat("1234", prompt);
    }
}
