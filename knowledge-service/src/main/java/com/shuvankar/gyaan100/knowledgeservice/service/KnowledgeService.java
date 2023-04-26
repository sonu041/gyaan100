package com.shuvankar.gyaan100.knowledgeservice.service;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeResponse;
import com.shuvankar.gyaan100.knowledgeservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import com.shuvankar.gyaan100.knowledgeservice.repository.KnowledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.knowledgeservice.config.AppConstants.ID;


@Service
@RequiredArgsConstructor
public class KnowledgeService {
    private final KnowledgeRepository knowledgeRepository;

    /** Add knowledge service */
    public KnowledgeResponse addKnowledge(KnowledgeRequest knowledgeRequest) {
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(knowledgeRequest.getTitle());
        knowledge.setDescription(knowledgeRequest.getDescription());
        knowledge.setActive("Y");
        knowledgeRepository.save(knowledge);
        return mapToKnowledgeResponse(knowledge);
    }

    /** Get All Knowledge Service */
    public List<KnowledgeResponse> getAllKnowledges() {
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        return knowledges.stream().map(this::mapToKnowledgeResponse).toList();
    }

    /** Update Knowledge Service */
    public void updateKnowledge(Long id, KnowledgeRequest knowledgeRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(knowledgeRequest, p);
            return knowledgeRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Knowledge", ID, id));
    }

    /** Soft Delete Knowledge. This is not yet used. We can use it later if required. */
    public void softDeleteKnowledge(Long id) throws ResourceNotFoundException {
        findById(id).map(p -> {
            p.setActive("N");
            return knowledgeRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Knowledge", ID, id));
    }

    /** Hard Delete Knowledge */
    public void deleteKnowledgeByID(Long id) {
        knowledgeRepository.deleteById(id);
    }

    /** Find Knowledge by id */
    public Optional<Knowledge> findById(Long id) {
        return knowledgeRepository.findById(id);
    }

    /** Mapper method to map Knowledge object with Response */
    private KnowledgeResponse mapToKnowledgeResponse(Knowledge knowledge) {
        return KnowledgeResponse.builder()
                .id(knowledge.getId())
                .title(knowledge.getTitle())
                .description(knowledge.getDescription())
                .build();
    }
}