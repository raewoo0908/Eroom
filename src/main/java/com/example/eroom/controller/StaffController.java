package com.example.eroom.controller;

import com.example.eroom.dto.StaffDTO;
import com.example.eroom.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class StaffController {

    @Autowired private StaffService staffService;

    /*
    * 회원가입
    * */
    @PostMapping("/signup")
    public ResponseEntity<?> registerStaff(@RequestBody StaffDTO staffDTO) {

    }

    /*
    * 로그인
    * */
    @PostMapping("/signin")
    public ResponseEntity<?> loginStaff(@RequestBody StaffDTO staffDTO) {

    }


}
