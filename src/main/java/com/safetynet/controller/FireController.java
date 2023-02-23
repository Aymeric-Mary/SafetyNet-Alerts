package com.safetynet.controller;

import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.service.FireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FireController {

    private final FireService fireService;

    @GetMapping(value = "/fire", produces = "application/json")
    public ResponseEntity<FireResponseDto> getPeopleByAddress(
            @RequestParam("address") String address
    ) {
        log.info("GET /fire?address={}", address);
        FireResponseDto responseDto = fireService.getFireResponseDto(address);
        log.info("GET /fire?address={} - 200 OK", address);
        return ResponseEntity.ok(responseDto);
    }
}
