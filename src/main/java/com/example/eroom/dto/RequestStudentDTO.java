package com.example.eroom.dto;

import com.example.eroom.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestStudentDTO {
    private Long id;
    private String name;
    private String school;
    private int grade;
    private String mom_mobile;
    private String student_mobile;
    private String dad_mobile;


    public static Student toEntity(RequestStudentDTO requestStudentDTO) {
        return Student.builder()
                .studentName(requestStudentDTO.getName())
                .schoolName(requestStudentDTO.getSchool())
                .grade(requestStudentDTO.getGrade())
                .momMobile(requestStudentDTO.getMom_mobile())
                .studentMobile(requestStudentDTO.getStudent_mobile())
                .dadMobile(requestStudentDTO.getDad_mobile())
                .build();
    }
}
