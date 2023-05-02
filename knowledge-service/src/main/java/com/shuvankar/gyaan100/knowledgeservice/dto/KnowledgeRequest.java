package com.shuvankar.gyaan100.knowledgeservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeRequest {
    private String title;
    private String description;
    private String author;
//    private String created_date;

}