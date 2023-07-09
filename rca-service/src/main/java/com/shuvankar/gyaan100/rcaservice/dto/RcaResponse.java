package com.shuvankar.gyaan100.rcaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RcaResponse {
    private Long id;
    private String title;
    private String details;
    private String root_cause;
    private String timeline;
    private String action_item;
    private String created_date;

}
