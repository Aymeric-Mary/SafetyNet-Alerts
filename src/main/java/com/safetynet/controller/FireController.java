package com.safetynet.controller;

import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.service.FireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FireController {

    private final FireService fireService;

    @GetMapping(value = "/fire", produces = "application/json")
    ResponseEntity<FireResponseDto> getPeopleByAddress(
            @RequestParam("address") String address
    ) {
        FireResponseDto responseDto = fireService.getFireResponseDto(address);
        return ResponseEntity.ok(responseDto);
    }
}
