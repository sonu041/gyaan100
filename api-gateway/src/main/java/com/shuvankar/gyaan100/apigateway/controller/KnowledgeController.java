package com.shuvankar.gyaan100.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/knowledgetest")
@CrossOrigin(origins = "*")
public class KnowledgeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getKnowledge() {

        return "Getting Knowledge";
    }
}
