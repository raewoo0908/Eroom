package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter @Setter
public class Classes {

    @Id @GeneratedValue
    @Column(name = "class_id")
    private Long id;

    private String className;

    private Time startTime;

    private Time endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static Classes create(String className, Time startTime, Time endTime, Teacher teacher) {
        Classes classes = new Classes();
        classes.setClassName(className);
        classes.setStartTime(startTime);
        classes.setEndTime(endTime);
        classes.setTeacher(teacher);
        return classes;
    }
}
