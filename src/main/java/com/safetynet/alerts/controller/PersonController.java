package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.GetPeopleByStationResponseDto;
import com.safetynet.alerts.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/firestation", produces = "application/json")
    ResponseEntity<GetPeopleByStationResponseDto> getPeopleByStationNumber(
            @RequestParam("stationNumber") Integer station
    ) {
        GetPeopleByStationResponseDto responseDto = personService.getByStation(station);
        return ResponseEntity.ok(responseDto);
    }

}
