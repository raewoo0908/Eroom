package com.example.eroom.dto;

import com.example.eroom.domain.AdminWork;
import com.example.eroom.domain.Staff;
import com.example.eroom.domain.Student;
import com.example.eroom.domain.WorkStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {

    private Long id; //work의 id

    private String detail; //work의 내용

    private String status;

    private List<Long> studentIdList;

    private Long staffId;

    // 생성자
    // 엔티티 -> DTO : 화면에 뿌릴 때 필요
    public WorkDTO(AdminWork adminWork) {
        this.setId(adminWork.getId());
        this.setDetail(adminWork.getDetail());
        this.setStatus(adminWork.getStatus().toString());
        this.setStudentIdList(adminWork.getWorkStudentList()
                .stream()
                .map(ws -> ws.getStudent().getId())
                .toList());
        this.setStaffId(adminWork.getStaff().getId());
    }
}
