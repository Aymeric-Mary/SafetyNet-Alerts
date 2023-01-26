package com.safetynet.controller;

import com.safetynet.dto.floodStations.FloodStationsResponseDto;
import com.safetynet.service.FloodStationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FloodStationsController {

    private final FloodStationsService floodStationsService;

    @GetMapping(value = "/flood/stations", produces = "application/json")
    ResponseEntity<FloodStationsResponseDto> getPersonByListOfStations(
            @RequestParam List<Integer> stations
    ) {
        FloodStationsResponseDto responseDto = floodStationsService.getFloodStationsDto(stations);
        return ResponseEntity.ok(responseDto);
    }

}
