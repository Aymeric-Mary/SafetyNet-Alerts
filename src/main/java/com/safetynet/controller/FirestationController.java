package com.safetynet.controller;

import com.safetynet.dto.fireStation.*;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.model.FireStation;
import com.safetynet.service.FireStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FirestationController {

    private final FireStationService fireStationService;
    private final FireStationMapper fireStationMapper;

    @GetMapping(value = "/firestation", produces = "application/json")
    public ResponseEntity<GetFireStationResponseDto> getPeopleByStationNumber(
            @RequestParam("stationNumber") Integer station
    ) {
        log.info("GET /firestation?stationNumber={}", station);
        GetFireStationResponseDto responseDto = fireStationService.getByStation(station);
        log.info("GET /firestation?stationNumber={} - 200 OK ", station);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/firestations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<FireStationResponseDto> createFireStation(
            @RequestBody CreateFireStationRequestDto requestDto
    ){
        log.info("POST /firestations ");
        FireStation fireStation = fireStationService.createFireStation(requestDto);
        FireStationResponseDto responseDto = fireStationMapper.toResponseDto(fireStation);
        log.info("POST /firestations - 201 Created ");
        return ResponseEntity.status(201).body(responseDto);
    }

    @DeleteMapping(value = "/firestations", consumes = "application/json")
    public ResponseEntity<Void> deleteFireStation(
            @RequestBody DeleteFireStationRequestDto requestDto
    ){
        log.info("DELETE /firestations");
        fireStationService.deleteFireStation(requestDto);
        log.info("DELETE /firestations - 204 No Content");
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/firestations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<FireStationResponseDto> updateFireStation(
            @RequestBody UpdateFireStationRequestDto requestDto
    ){
        log.info("PUT /firestations");
        FireStation fireStation = fireStationService.updateFireStation(requestDto);
        FireStationResponseDto responseDto = fireStationMapper.toResponseDto(fireStation);
        log.info("PUT /firestations - 200 OK");
        return ResponseEntity.ok(responseDto);
    }

}
