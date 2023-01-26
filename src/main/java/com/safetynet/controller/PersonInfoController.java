package com.safetynet.controller;

import com.safetynet.dto.personInfo.PersonInfoResponseDto;
import com.safetynet.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonInfoController {

    private final PersonInfoService personInfoService;

    @GetMapping(value = "/personInfo", produces = "application/json")
    ResponseEntity<PersonInfoResponseDto> getPersonInfo(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
    ) {
        PersonInfoResponseDto responseDto = personInfoService.getPersonInfoResponseDto(firstName, lastName);
        return ResponseEntity.ok(responseDto);
    }
}
