package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Attendance {

    @Id @GeneratedValue
    @Column(name = "attedance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private boolean isAttend;

    private LocalDate classDate;

    @Enumerated(EnumType.STRING)
    private AbsenceReasonEnum absenceReason;

    public static Attendance createAttendance(Student student, Classes classes, Payment payment) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setClasses(classes);
        attendance.setPayment(payment);
        return attendance;
    }
}
