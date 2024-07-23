package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class AdminWork {

    @Id @GeneratedValue
    @Column(name = "work_id")
    private Long id;

    private LocalDateTime recordDateTime; //업무 기록 시각

    @Column(columnDefinition = "LONGTEXT")
    private String detail; //업무 세부사항

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff; //업무 기록 스태프

    @Enumerated(EnumType.STRING)
    private WorkStatus status; //업무 진행 상태 (COMPLETED, ONGOING)

    @OneToMany(mappedBy = "adminWork")
    private List<WorkStudent> workStudentList = new ArrayList<>();


    //생성 메서드
    public static AdminWork createAdminWork(Staff staff, String detail) {
        AdminWork adminWork = new AdminWork();
        adminWork.staff = staff;
        adminWork.detail = detail;
        adminWork.recordDateTime = LocalDateTime.now();
        adminWork.status = WorkStatus.ONGOING;
        return adminWork;
    }


}
