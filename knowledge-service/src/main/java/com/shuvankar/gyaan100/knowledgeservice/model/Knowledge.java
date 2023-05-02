package com.shuvankar.gyaan100.knowledgeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="knowledges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String active;
    private String author;
    private String created_date;
}
