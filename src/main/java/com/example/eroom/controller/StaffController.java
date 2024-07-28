package com.example.eroom.controller;

import com.example.eroom.domain.Staff;
import com.example.eroom.dto.ResponseDTO;
import com.example.eroom.dto.StaffDTO;
import com.example.eroom.service.StaffService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired private StaffService staffService;

    /*
    * 회원가입
    * param: StaffDTO
    * return: ResponseEntity.ok().body(StaffDTO) || ResponseEntity.badRequest().body(ResponseDTO)
    * */
    @PostMapping("/signup")
    public ResponseEntity<?> registerStaff(@RequestBody @Valid StaffDTO staffDTO) {
        try{
            if(staffDTO == null || staffDTO.getPassword() == null){
                throw new RuntimeException("Invalid Password value.");
            }
            //Staff 엔티티 생성
            Staff staff = Staff.builder()
                    .staffName(staffDTO.getStaffName())
                    .email(staffDTO.getEmail())
                    .password(staffDTO.getPassword())
                    .build();
            //Staff 엔티티 db에 저장
            staffService.addStaff(staff);

            //StaffDTO 초기화
            StaffDTO responseStaffDTO = StaffDTO.builder()
                    .id(staff.getId())
                    .staffName(staff.getStaffName())
                    .email(staff.getEmail())
                    .build();
            //ResponseEntity에 StaffDTO 실어서 리턴
            return ResponseEntity.ok().body(responseStaffDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    /*
    * 로그인
    * param: StaffDTO
    * return: ResponseEntity.ok().body(StaffDTO) || ResponseEntity.badRequest().body(ResponseDTO)
    * */
    @PostMapping("/signin")
    public ResponseEntity<?> loginStaff(@RequestBody @Valid StaffDTO staffDTO) {
        Staff staff = staffService.findStaffByCredentials(staffDTO.getEmail(), staffDTO.getPassword()).get();
        if (staff != null) {
            StaffDTO responseStaffDTO = StaffDTO.builder()
                    .staffName(staff.getStaffName())
                    .email(staff.getEmail())
                    .id(staff.getId())
                    .build();
            return ResponseEntity.ok().body(responseStaffDTO);
        } else{
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


}
