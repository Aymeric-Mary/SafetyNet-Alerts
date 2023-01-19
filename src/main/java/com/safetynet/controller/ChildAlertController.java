package com.safetynet.controller;

import com.safetynet.dto.childAlert.ChildAlertResponseDto;
import com.safetynet.service.ChildAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChildAlertController {

    private final ChildAlertService childAlertService;

    @GetMapping(value = "/childAlert", produces = "application/json")
    ResponseEntity<ChildAlertResponseDto> getChildrenByAddress(
            @RequestParam String address
    ) {
        ChildAlertResponseDto responseDto = childAlertService.getChildrenByAddress(address);
        if (responseDto.getChildren().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseDto);
    }

}
