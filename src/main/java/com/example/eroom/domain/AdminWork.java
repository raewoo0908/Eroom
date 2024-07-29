package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private WorkStatusEnum status; //업무 진행 상태 (COMPLETED, ONGOING)

    @OneToMany(mappedBy = "adminWork")
    private List<WorkStudent> workStudentList = new ArrayList<>();

    //연관관계 편의 메서드
    public void addWorkStudent(WorkStudent workStudent) {
        workStudentList.add(workStudent);
        workStudent.setAdminWork(this);
    }


    //생성 메서드
    /*생성 메서드
    * param: 업무 생성 Staff, 세부사항, 관련된 Student 리스트
    * return: 새로 생성된 AdminWork
    * */
    public static AdminWork createAdminWork(Staff staff, String detail, List<WorkStudent> workStudentList){
        AdminWork adminWork = new AdminWork();
        adminWork.setStaff(staff);
        adminWork.setDetail(detail);
        adminWork.setRecordDateTime(LocalDateTime.now());
        adminWork.setStatus(WorkStatusEnum.ONGOING);
        for (WorkStudent workStudent : workStudentList) {
            adminWork.addWorkStudent(workStudent);
        }
        return adminWork;
    }

    //==비즈니스 로직==//
}
