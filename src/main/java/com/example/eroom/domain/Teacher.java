package com.example.eroom.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

    private String teacherName;

    @Enumerated(EnumType.STRING)
    private SubjectEnum subject;

    @OneToMany(mappedBy = "teacher")
    private List<Classes> classesList = new ArrayList<>();
}

