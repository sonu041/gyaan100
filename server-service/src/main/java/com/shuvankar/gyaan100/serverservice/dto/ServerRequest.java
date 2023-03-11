package com.shuvankar.gyaan100.serverservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerRequest {
    private String name;
    private String hostname;
    private String ip;
    private String type;
    private String os;
    private String status;

}