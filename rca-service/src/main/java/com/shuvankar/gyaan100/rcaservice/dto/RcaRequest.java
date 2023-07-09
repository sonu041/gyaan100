package com.shuvankar.gyaan100.rcaservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RcaRequest {
    private String title;
    private String details;
    private String root_cause;
    private String timeline;
    private String action_item;
}