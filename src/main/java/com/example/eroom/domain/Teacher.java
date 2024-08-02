package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

    private String teacherName;

    @Enumerated(EnumType.STRING)
    private SubjectEnum subject;

    private String mobile;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classes> classesList = new ArrayList<>();

    //연관관계 편의 메서드
    public void addClasses(Classes classes) {
        this.classesList.add(classes);
        classes.setTeacher(this);
    }

    public void clearClassList() {
        this.classesList.clear();
    }

    //생성메서드
    public static Teacher createTeacher(String name, String mobile, String subject, List<Classes> classesList) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(name);
        teacher.setMobile(mobile);
        switch(subject){
            case "KOREAN": teacher.setSubject(SubjectEnum.KOREAN);
            case "ENGLISH": teacher.setSubject(SubjectEnum.ENGLISH);
            case "MATH": teacher.setSubject(SubjectEnum.MATH);
            case "SCIENCE": teacher.setSubject(SubjectEnum.SCIENCE);
        }
        for(Classes c : classesList){
            teacher.addClasses(c);
        }
        return teacher;
    }
}

