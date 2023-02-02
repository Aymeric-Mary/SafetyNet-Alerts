package com.safetynet.controller;

import com.safetynet.dto.medicalRecord.DeleteMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.MedicalRecordResponseDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecords")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;

    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto> createMedicalRecord(
            @RequestBody CreateMedicalRecordRequestDto requestDto
    ) {
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(requestDto);
        MedicalRecordResponseDto responseDto = medicalRecordMapper.toResponseDto(medicalRecord);
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<MedicalRecordResponseDto> updateMedicalRecord(
            @RequestBody UpdateMedicalRecordRequestDto requestDto
    ) {
        MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(requestDto);
        MedicalRecordResponseDto responseDto = medicalRecordMapper.toResponseDto(medicalRecord);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMedicalRecord(
            @RequestBody DeleteMedicalRecordRequestDto requestDto
    ) {
        medicalRecordService.deleteMedicalRecord(requestDto);
        return ResponseEntity.noContent().build();
    }
}
