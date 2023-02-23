package com.safetynet.controller;

import com.safetynet.dto.phoneAlert.PhoneAlertResponseDto;
import com.safetynet.service.PhoneAlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PhoneAlertController {

    private final PhoneAlertService phoneAlertService;

    @GetMapping(value = "/phoneAlert", produces = "application/json")
    public ResponseEntity<PhoneAlertResponseDto> getPhonesByStation(
            @RequestParam(value = "firestation") Integer fireStationNumber
    ) {
        log.info("GET /phoneAlert?firestation={}", fireStationNumber);
        PhoneAlertResponseDto responseDto = phoneAlertService.getPhoneAlertResponseDto(fireStationNumber);
        log.info("GET /phoneAlert?firestation={} - 200 OK", fireStationNumber);
        return ResponseEntity.ok(responseDto);
    }
}
