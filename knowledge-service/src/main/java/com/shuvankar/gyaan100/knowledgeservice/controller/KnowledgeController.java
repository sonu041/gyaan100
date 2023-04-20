package com.shuvankar.gyaan100.knowledgeservice.controller;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeResponse;
import com.shuvankar.gyaan100.knowledgeservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import com.shuvankar.gyaan100.knowledgeservice.service.KnowledgeService;
import com.shuvankar.gyaan100.knowledgeservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.shuvankar.gyaan100.knowledgeservice.config.AppConstants.ID;


@RestController
@RequestMapping("/api/knowledge")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class KnowledgeController {
    private final KnowledgeService knowledgeService;

    /** Create Knowledge */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KnowledgeResponse> addKnowledge(@RequestBody KnowledgeRequest knowledgeRequest) {
        KnowledgeResponse response = new KnowledgeResponse();
        response = knowledgeService.addKnowledge(knowledgeRequest);
        return new ResponseEntity<KnowledgeResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
    }

    /** Get single knowledge */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Knowledge getKnowledge(@PathVariable Long id ) throws ResourceNotFoundException {
//        return knowledgeService.getKnowledge();
        return knowledgeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }

    /** Get all knowledge */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<KnowledgeResponse> getAllKnowledges() {
        return knowledgeService.getAllKnowledges();
    }

    /** Update Knowledge */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateKnowledge(@PathVariable Long id, @RequestBody KnowledgeRequest knowledgeRequest) throws ResourceNotFoundException {
        knowledgeService.updateKnowledge(id, knowledgeRequest);
    }

    /** Delete Knowledge */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteKnowledge(@PathVariable Long id) throws ResourceNotFoundException {
        knowledgeService.findById(id).map(p -> {
            knowledgeService.deleteKnowledgeByID(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }
}
