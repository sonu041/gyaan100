package com.shuvankar.gyaan100.templateservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequest {
    private String title;
    private String description;
    private String content;

}