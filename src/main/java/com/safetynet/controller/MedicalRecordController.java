package com.safetynet.controller;

import com.safetynet.dto.medicalRecord.DeleteMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.MedicalRecordResponseDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecords")
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;

    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto> createMedicalRecord(
            @RequestBody CreateMedicalRecordRequestDto requestDto
    ) {
        log.info("POST /medicalRecords - {}", requestDto);
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(requestDto);
        MedicalRecordResponseDto responseDto = medicalRecordMapper.toResponseDto(medicalRecord);
        log.info("POST /medicalRecords - 201 Created - {}", responseDto);
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<MedicalRecordResponseDto> updateMedicalRecord(
            @RequestBody UpdateMedicalRecordRequestDto requestDto
    ) {
        log.info("PUT /medicalRecords - {}", requestDto);
        MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(requestDto);
        MedicalRecordResponseDto responseDto = medicalRecordMapper.toResponseDto(medicalRecord);
        log.info("PUT /medicalRecords - 200 OK - {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMedicalRecord(
            @RequestBody DeleteMedicalRecordRequestDto requestDto
    ) {
        log.info("DELETE /medicalRecords - {}", requestDto);
        medicalRecordService.deleteMedicalRecord(requestDto);
        log.info("DELETE /medicalRecords - 204 No Content");
        return ResponseEntity.noContent().build();
    }
}
