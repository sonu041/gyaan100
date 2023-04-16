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

    public void addKnowledge(KnowledgeRequest knowledgeRequest) {
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(knowledgeRequest.getTitle());
        knowledge.setDescription(knowledgeRequest.getDescription());
//		Knowledge knowledge = Knowledge.builder

        knowledgeRepository.save(knowledge);
    }

    public List<KnowledgeResponse> getAllKnowledges() {
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        return knowledges.stream().map(this::mapToKnowledgeResponse).toList();
    }

    public void updateKnowledge(Long id, KnowledgeRequest knowledgeRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(knowledgeRequest, p);

            return knowledgeRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));


    }

    public Optional<Knowledge> findById(Long id) {
        return knowledgeRepository.findById(id);
    }

    private KnowledgeResponse mapToKnowledgeResponse(Knowledge knowledge) {
        return KnowledgeResponse.builder()
                .title(knowledge.getTitle())
                .description(knowledge.getDescription())
                .build();
    }
}
