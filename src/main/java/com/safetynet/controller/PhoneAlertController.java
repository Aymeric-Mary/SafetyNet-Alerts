package com.safetynet.controller;

import com.safetynet.dto.phoneAlert.PhoneAlertResponseDto;
import com.safetynet.service.PhoneAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneAlertController {

    private final PhoneAlertService phoneAlertService;

    @GetMapping(value = "/phoneAlert", produces = "application/json")
    ResponseEntity<PhoneAlertResponseDto> getPhonesByStation(
            @RequestParam("firestation") Integer fireStationNumber
    ) {
        PhoneAlertResponseDto responseDto = phoneAlertService.getPhoneAlertResponseDto(fireStationNumber);
        return ResponseEntity.ok(responseDto);
    }
}
