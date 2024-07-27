package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class WorkStudent {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private AdminWork adminWork;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public static WorkStudent createWorkStudent(Student student){
        WorkStudent workStudent = new WorkStudent();
        workStudent.setStudent(student);
        //setAdminWork는 AdminWork의 생성 메서드에서 해줌.
        return workStudent;
    }
}
