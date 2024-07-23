package com.example.eroom.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Staff {

    @Id @GeneratedValue
    @Column(name = "staff_id")
    private Long id;

    private String staffName; //스태프 이름


    //생성 메서드
    public static Staff createStaff(String staffName) {
        Staff staff = new Staff();
        staff.staffName = staffName;
        return staff;
    }

}
