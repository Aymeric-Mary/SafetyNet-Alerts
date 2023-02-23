package com.safetynet.controller;

import com.safetynet.dto.personInfo.PersonInfoResponseDto;
import com.safetynet.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonInfoController {

    private final PersonInfoService personInfoService;

    @GetMapping(value = "/personInfo", produces = "application/json")
    public ResponseEntity<PersonInfoResponseDto> getPersonInfo(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
    ) {
        log.info("GET /personInfo?firstName={}&lastName={}", firstName, lastName);
        PersonInfoResponseDto responseDto = personInfoService.getPersonInfoResponseDto(firstName, lastName);
        log.info("GET /personInfo?firstName={}&lastName={} - 200 OK", firstName, lastName);
        return ResponseEntity.ok(responseDto);
    }
}
