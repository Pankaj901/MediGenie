package com.citiustech.MediGenie.controller;

import com.citiustech.MediGenie.service.GenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final GenAIService genAIService;

    public ChatbotController(GenAIService genAIService) {
        this.genAIService = genAIService;
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, String>> askChatbot(@RequestBody Map<String, String> request) {
        String userQuery = request.get("query");
        String aiResponse = genAIService.getAIResponse(userQuery);
        return ResponseEntity.ok(Map.of("response", aiResponse));
    }
}

