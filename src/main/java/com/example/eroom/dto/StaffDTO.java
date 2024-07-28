package com.example.eroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private String token;
    private String staffName;
    private String email;
    private Long id;
    private String password;
}
