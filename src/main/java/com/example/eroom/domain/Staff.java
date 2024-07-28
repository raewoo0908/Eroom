package com.example.eroom.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id @GeneratedValue
    @Column(name = "staff_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    private String staffName; //스태프 이름

    private String password;

    @Enumerated(EnumType.STRING)
    private StaffRoleEnum role; //스태프 권한(STAFF, ADMIN)

    private String authProvider; //oAuth에서 사용할 유저 정보 제공자




    //생성 메서드
    public static Staff createStaff(String staffName, String staffEmail) {
        Staff staff = new Staff();
        staff.setStaffName(staffName);
        staff.setEmail(staffEmail);
        return staff;
    }

}
