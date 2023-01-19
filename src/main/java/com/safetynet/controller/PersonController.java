package com.safetynet.controller;

import com.safetynet.dto.getChildrenByAddress.GetChildrenByAddressResponseDto;
import com.safetynet.dto.GetPeopleByStationResponseDto;
import com.safetynet.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/firestation", produces = "application/json")
    ResponseEntity<GetPeopleByStationResponseDto> getPeopleByStationNumber(
            @RequestParam("stationNumber") Integer station
    ) {
        GetPeopleByStationResponseDto responseDto = personService.getByStation(station);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/childAlert", produces = "application/json")
    ResponseEntity<GetChildrenByAddressResponseDto> getChildrenByAddress(
            @RequestParam String address
    ) {
        GetChildrenByAddressResponseDto responseDto = personService.getChildrenByAddress(address);
        if (responseDto.getChildren().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseDto);
    }

}
