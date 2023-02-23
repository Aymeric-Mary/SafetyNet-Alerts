package com.safetynet.controller;

import com.safetynet.dto.communityEmail.CommunityEmailResponseDto;
import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.service.CommunityEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommunityEmailController {

    private final CommunityEmailService communityEmailService;

    @GetMapping(value = "/communityEmail", produces = "application/json")
    public ResponseEntity<CommunityEmailResponseDto> getEmailsByCity(
            @RequestParam String city
    ) {
        log.info("GET /communityEmail?city={}", city);
        CommunityEmailResponseDto responseDto = communityEmailService.getCommunityResponseDto(city);
        log.info("GET /communityEmail?city={} - 200 OK", city);
        return ResponseEntity.ok(responseDto);
    }

}
