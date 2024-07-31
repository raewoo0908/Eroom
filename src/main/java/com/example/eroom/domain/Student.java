package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private String studentName;

    private String schoolName;

    private int grade;

    private String studentMobile;

    private String momMobile;

    private String dadMobile;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendanceList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<WorkStudent> workStudentList = new ArrayList<>();
}
