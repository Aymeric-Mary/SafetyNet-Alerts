package com.safetynet.controller;

import com.safetynet.dto.childAlert.ChildAlertResponseDto;
import com.safetynet.service.ChildAlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChildAlertController {

    private final ChildAlertService childAlertService;

    @GetMapping(value = "/childAlert", produces = "application/json")
    public ResponseEntity<ChildAlertResponseDto> getChildrenByAddress(
            @RequestParam String address
    ) {
        log.info("GET /childAlert?address={}", address);
        ChildAlertResponseDto responseDto = childAlertService.getChildrenByAddress(address);
        if (responseDto.getChildren().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("GET /childAlert?address={} - 200 OK", address);
        return ResponseEntity.ok(responseDto);
    }

}
