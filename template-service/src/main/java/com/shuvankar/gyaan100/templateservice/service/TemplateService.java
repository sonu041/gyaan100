package com.shuvankar.gyaan100.templateservice.service;

import com.shuvankar.gyaan100.templateservice.dto.TemplateRequest;
import com.shuvankar.gyaan100.templateservice.dto.TemplateResponse;
import com.shuvankar.gyaan100.templateservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.templateservice.model.Template;
import com.shuvankar.gyaan100.templateservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.templateservice.config.AppConstants.ID;


@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    /** Add template service */
    public TemplateResponse addTemplate(TemplateRequest templateRequest) {
        Template template = new Template();
        template.setTitle(templateRequest.getTitle());
        template.setDescription(templateRequest.getDescription());
        template.setContent(templateRequest.getContent());
        templateRepository.save(template);
        return mapToTemplateResponse(template);
    }

    /** Get All Template Service */
    public List<TemplateResponse> getAllTemplates() {
        List<Template> templates = templateRepository.findAll();
        return templates.stream().map(this::mapToTemplateResponse).toList();
    }

    /** Update Template Service */
    public void updateTemplate(Long id, TemplateRequest templateRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(templateRequest, p);
            return templateRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Template", ID, id));
    }

    /** Hard Delete Template */
    public void deleteTemplateByID(Long id) {
        templateRepository.deleteById(id);
    }

    /** Find Template by id */
    public Optional<Template> findById(Long id) {
        return templateRepository.findById(id);
    }

    /** Mapper method to map Template object with Response */
    private TemplateResponse mapToTemplateResponse(Template template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .title(template.getTitle())
                .description(template.getDescription())
                .build();
    }
}