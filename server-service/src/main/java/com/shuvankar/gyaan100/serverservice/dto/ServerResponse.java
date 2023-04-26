package com.shuvankar.gyaan100.serverservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerResponse {
    private Long id;
    private String name;
    private String hostname;
    private String ip;
    private String type;
    private String os;
    private String status;
}
