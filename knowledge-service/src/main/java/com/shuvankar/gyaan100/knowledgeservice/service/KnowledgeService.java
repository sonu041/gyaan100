package com.shuvankar.gyaan100.knowledgeservice.service;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeResponse;
import com.shuvankar.gyaan100.knowledgeservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import com.shuvankar.gyaan100.knowledgeservice.repository.KnowledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.knowledgeservice.config.AppConstants.ID;


@Service
@RequiredArgsConstructor
public class KnowledgeService {
    private final KnowledgeRepository knowledgeRepository;

    public KnowledgeResponse addKnowledge(KnowledgeRequest knowledgeRequest) {
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(knowledgeRequest.getTitle());
        knowledge.setDescription(knowledgeRequest.getDescription());
        knowledge.setActive("Y");
        knowledgeRepository.save(knowledge);
        return mapToKnowledgeResponse(knowledge);
    }

    public List<KnowledgeResponse> getAllKnowledges() {
//        List<Knowledge> knowledges = knowledgeRepository.findActiveAll("Y");
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        return knowledges.stream().map(this::mapToKnowledgeResponse).toList();
    }

    public void updateKnowledge(Long id, KnowledgeRequest knowledgeRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(knowledgeRequest, p);
            return knowledgeRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }

    public void deleteKnowledge(Long id) throws ResourceNotFoundException {
        findById(id).map(p -> {
            p.setActive("N");   //Soft delete
            return knowledgeRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Knowledge", ID, id));
    }

    public Optional<Knowledge> findById(Long id) {
        return knowledgeRepository.findById(id);
    }

    private KnowledgeResponse mapToKnowledgeResponse(Knowledge knowledge) {
        return KnowledgeResponse.builder()
                .id(knowledge.getId())
                .title(knowledge.getTitle())
                .description(knowledge.getDescription())
                .build();
    }
}
