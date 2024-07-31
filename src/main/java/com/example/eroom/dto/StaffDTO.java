package com.example.eroom.dto;

import com.example.eroom.domain.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    //private String token;
    private String staffName;
    private String email;
    private Long id;
    private String password;

    //Entity -> DTO
    public StaffDTO(Staff staff){
        setStaffName(staff.getStaffName());
        setEmail(staff.getEmail());
    }
}
