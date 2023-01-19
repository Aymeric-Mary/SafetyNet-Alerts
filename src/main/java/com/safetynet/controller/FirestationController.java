package com.safetynet.controller;

import com.safetynet.dto.childAlert.ChildAlertResponseDto;
import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.dto.fireStation.FireStationResponseDto;
import com.safetynet.service.FireStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FirestationController {

    private final FireStationService fireStationService;

    @GetMapping(value = "/firestation", produces = "application/json")
    ResponseEntity<FireStationResponseDto> getPeopleByStationNumber(
            @RequestParam("stationNumber") Integer station
    ) {
        FireStationResponseDto responseDto = fireStationService.getByStation(station);
        return ResponseEntity.ok(responseDto);
    }

}
