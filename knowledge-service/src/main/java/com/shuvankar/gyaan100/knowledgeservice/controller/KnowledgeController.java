package com.shuvankar.gyaan100.knowledgeservice.controller;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeResponse;
import com.shuvankar.gyaan100.knowledgeservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.knowledgeservice.service.KnowledgeService;
import com.shuvankar.gyaan100.knowledgeservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class KnowledgeController {
    private final KnowledgeService knowledgeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KnowledgeResponse> addKnowledge(@RequestBody KnowledgeRequest knowledgeRequest) {
        KnowledgeResponse response = new KnowledgeResponse();
        response = knowledgeService.addKnowledge(knowledgeRequest);
        return new ResponseEntity<KnowledgeResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
//        return "[{ 'Message': 'Knowledge Added Successfully' }]";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<KnowledgeResponse> getKnowledge() {
        return knowledgeService.getAllKnowledges();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateKnowledge(@PathVariable Long id, @RequestBody KnowledgeRequest knowledgeRequest) throws ResourceNotFoundException {
        knowledgeService.updateKnowledge(id, knowledgeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteKnowledge(@PathVariable Long id) throws ResourceNotFoundException {

        knowledgeService.deleteKnowledge(id);
    }
}
