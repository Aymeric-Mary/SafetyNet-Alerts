package com.safetynet.controller;

import com.safetynet.dto.fireStation.*;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.model.FireStation;
import com.safetynet.service.FireStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FirestationController {

    private final FireStationService fireStationService;
    private final FireStationMapper fireStationMapper;

    @GetMapping(value = "/firestation", produces = "application/json")
    ResponseEntity<GetFireStationResponseDto> getPeopleByStationNumber(
            @RequestParam("stationNumber") Integer station
    ) {
        GetFireStationResponseDto responseDto = fireStationService.getByStation(station);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/firestations", consumes = "application/json", produces = "application/json")
    ResponseEntity<FireStationResponseDto> createFireStation(
            @RequestBody CreateFireStationRequestDto requestDto
    ){
        FireStation fireStation = fireStationService.createFireStation(requestDto);
        FireStationResponseDto responseDto = fireStationMapper.toResponseDto(fireStation);
        return ResponseEntity.status(201).body(responseDto);
    }

    @DeleteMapping(value = "/firestations", consumes = "application/json")
    ResponseEntity<Void> deleteFireStation(
            @RequestBody DeleteFireStationRequestDto requestDto
    ){
        fireStationService.deleteFireStation(requestDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/firestations", consumes = "application/json", produces = "application/json")
    ResponseEntity<FireStationResponseDto> updateFireStation(
            @RequestBody UpdateFireStationRequestDto requestDto
    ){
        FireStation fireStation = fireStationService.updateFireStation(requestDto);
        FireStationResponseDto responseDto = fireStationMapper.toResponseDto(fireStation);
        return ResponseEntity.ok(responseDto);
    }

}
