package com.safetynet.controller;

import com.safetynet.dto.communityEmail.CommunityEmailResponseDto;
import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.service.CommunityEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityEmailController {

    private final CommunityEmailService communityEmailService;

    @GetMapping(value = "/communityEmail", produces = "application/json")
    ResponseEntity<CommunityEmailResponseDto> getEmailsByCity(
            @RequestParam String city
    ) {
        CommunityEmailResponseDto responseDto = communityEmailService.getCommunityResponseDto(city);
        return ResponseEntity.ok(responseDto);
    }

}
