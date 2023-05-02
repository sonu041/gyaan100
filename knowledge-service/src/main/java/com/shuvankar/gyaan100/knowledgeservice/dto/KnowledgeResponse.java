package com.shuvankar.gyaan100.knowledgeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeResponse {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String created_date;

}
