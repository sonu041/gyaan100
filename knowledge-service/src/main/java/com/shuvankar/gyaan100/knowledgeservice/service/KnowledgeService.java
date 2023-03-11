package com.shuvankar.gyaan100.knowledgeservice.service;

import com.shuvankar.gyaan100.knowledgeservice.dto.KnowledgeRequest;
import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import com.shuvankar.gyaan100.knowledgeservice.repository.KnowledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
