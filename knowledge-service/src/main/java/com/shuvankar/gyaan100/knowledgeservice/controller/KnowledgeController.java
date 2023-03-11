package com.shuvankar.gyaan100.knowledgeservice.controller;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.service.KnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/knowledge")
@RequiredArgsConstructor
public class KnowledgeController {
    private final KnowledgeService knowledgeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addKnowledge(@RequestBody KnowledgeRequest knowledgeRequest) {
        knowledgeService.addKnowledge(knowledgeRequest);
        return "Knowledge Added Successfully";
    }
}
