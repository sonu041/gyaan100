package com.shuvankar.gyaan100.templateservice.controller;

import com.shuvankar.gyaan100.templateservice.dto.TemplateRequest;
import com.shuvankar.gyaan100.templateservice.dto.TemplateResponse;
import com.shuvankar.gyaan100.templateservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.templateservice.model.Template;
import com.shuvankar.gyaan100.templateservice.service.TemplateService;
import com.shuvankar.gyaan100.templateservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shuvankar.gyaan100.templateservice.config.AppConstants.ID;


@RestController
@RequestMapping("/api/template")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    /** Create Template */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TemplateResponse> addTemplate(@RequestBody TemplateRequest templateRequest) {
        TemplateResponse response = new TemplateResponse();
        response = templateService.addTemplate(templateRequest);
        return new ResponseEntity<TemplateResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
    }

    /** Get single template */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Template getTemplate(@PathVariable Long id ) throws ResourceNotFoundException {
//        return templateService.getTemplate();
        return templateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Template", ID, id));
    }

    /** Get all template */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TemplateResponse> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    /** Update Template */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTemplate(@PathVariable Long id, @RequestBody TemplateRequest templateRequest) throws ResourceNotFoundException {
        templateService.updateTemplate(id, templateRequest);
    }

    /** Delete Template */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTemplate(@PathVariable Long id) throws ResourceNotFoundException {
        templateService.findById(id).map(p -> {
            templateService.deleteTemplateByID(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }
}
