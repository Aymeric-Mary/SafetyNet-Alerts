package com.safetynet.controller;

import com.safetynet.dto.floodStations.FloodStationsResponseDto;
import com.safetynet.service.FloodStationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FloodStationsController {

    private final FloodStationsService floodStationsService;

    @GetMapping(value = "/flood/stations", produces = "application/json")
    public ResponseEntity<FloodStationsResponseDto> getPersonByListOfStations(
            @RequestParam List<Integer> stations
    ) {
        log.info("GET /flood/stations?stations={}", stations);
        FloodStationsResponseDto responseDto = floodStationsService.getFloodStationsDto(stations);
        log.info("GET /flood/stations?stations={} - 200 OK", stations);
        return ResponseEntity.ok(responseDto);
    }

}
