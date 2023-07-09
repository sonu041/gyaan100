package com.shuvankar.gyaan100.contactservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private String name;
    private String email;
    private String phone;
    private String team;

}