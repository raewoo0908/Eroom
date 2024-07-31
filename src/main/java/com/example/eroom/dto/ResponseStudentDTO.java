package com.example.eroom.dto;

import com.example.eroom.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStudentDTO {
    private Long id;
    private String name;
    private String school;
    private int grade;
    private String mom_mobile;
    private String student_mobile;
    private String dad_mobile;
    private List<AttendanceDTO> attendanceDTOs;

    public ResponseStudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getStudentName();
        this.school = student.getSchoolName();
        this.grade = student.getGrade();
        this.mom_mobile = student.getMomMobile();
        this.student_mobile = student.getStudentMobile();
        this.dad_mobile = student.getDadMobile();
        this.attendanceDTOs = student.getAttendanceList()
                .stream()
                .map(a -> new AttendanceDTO(a))
                .toList();
    }
}
