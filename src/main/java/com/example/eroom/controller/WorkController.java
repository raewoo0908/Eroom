package com.example.eroom.controller;

import com.example.eroom.domain.AdminWork;
import com.example.eroom.dto.ResponseDTO;
import com.example.eroom.dto.WorkDTO;
import com.example.eroom.repository.StaffRepository;
import com.example.eroom.repository.WorkRepository;
import com.example.eroom.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("work")
public class WorkController {

    private final WorkService workService;
    private final WorkRepository workRepository;
    private final StaffRepository staffRepository;
    private final ToEntity toEntity;

    /*
    * 행정업무 생성
    * param: 새로 생성해야 할 WorkDTO
    * return: 신규 업무가 추가된 List of all adminWorks
    * */
    @PostMapping
    public ResponseEntity<?> createWork(@RequestBody WorkDTO workDTO) {
        try{
            //AdminWork 생성
            AdminWork adminWork = toEntity.toAdminWorkEntity(workDTO);

            List<AdminWork> allAdminWorks = workService.createWork(adminWork);

            //모든 행정업무를 List<DTO>로 변환
            List<WorkDTO> workDTOList = allAdminWorks.stream()
                    .map(a -> new WorkDTO(a))
                    .toList();

            //응답DTO에 담음
            ResponseDTO<WorkDTO> responseDTO = ResponseDTO.<WorkDTO>builder().data(workDTOList).build();
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllWork() {
        try{
            //모든 행정업무 리스트 반환
            List<AdminWork> allAdminWorks = workRepository.findAll();

            //모든 행정업무를 List<DTO>로 변환
            List<WorkDTO> workDTOList = allAdminWorks.stream()
                    .map(adminWork -> new WorkDTO(adminWork))
                    .toList();

            //응답DTO에 담음
            ResponseDTO<WorkDTO> responseDTO = ResponseDTO.<WorkDTO>builder().data(workDTOList).build();
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateWork(@RequestBody WorkDTO workDTO) {
        try {
            //update 실행 후 반환된 모든 AdminWork 리스트
            List<AdminWork> allAdminWorks = workService.updateWork(workDTO);

            //모든 행정업무를 List<DTO>로 변환
            List<WorkDTO> workDTOList = allAdminWorks.stream()
                    .map(adminWork -> new WorkDTO(adminWork))
                    .toList();

            //응답DTO에 담음
            ResponseDTO<WorkDTO> responseDTO = ResponseDTO.<WorkDTO>builder().data(workDTOList).build();

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }



    @DeleteMapping
    public ResponseEntity<?> deleteWork(@RequestBody WorkDTO workDTO) {
        try {
            //delete 실행 후 모든 AdminWork 리스트
            List<AdminWork> allAdminWorks = workService.deleteWork(workDTO.getId());

            //모든 행정업무를 List<DTO>로 변환
            List<WorkDTO> workDTOList = allAdminWorks.stream()
                    .map(adminWork -> new WorkDTO(adminWork))
                    .toList();

            //응답DTO에 담음
            ResponseDTO<WorkDTO> responseDTO = ResponseDTO.<WorkDTO>builder().data(workDTOList).build();

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    //    @PutMapping("/status")
//    public ResponseEntity<?> changeStatus(@RequestBody WorkDTO workDTO, @RequestParam String changeToThis) {
//        List<AdminWork> allAdminWorks = workService.changeStatus(workDTO.getId(), changeToThis);
//
//        List<WorkDTO> workDTOList = allAdminWorks.stream()
//                .map(adminWork -> new WorkDTO(adminWork))
//                .toList();
//
//        ResponseDTO<WorkDTO> responseDTO = ResponseDTO.<WorkDTO>builder().data(workDTOList).build();
//        return ResponseEntity.ok(responseDTO);
//    }
}
