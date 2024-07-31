package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Classes {

    @Id @GeneratedValue
    @Column(name = "class_id")
    private Long id;

    private String className;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    //연관관계 편의 메서드//
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getClassesList().add(this);
    }

    public static Classes create(String className, LocalTime startTime, LocalTime endTime, Teacher teacher) {
        Classes classes = new Classes();
        classes.setClassName(className);
        classes.setStartTime(startTime);
        classes.setEndTime(endTime);
        classes.setTeacher(teacher);
        return classes;
    }
}
