package com.shuvankar.gyaan100.rcaservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="rca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private String root_cause;
    private String timeline;
    private String action_item;
    private String created_date;


}
