package com.safetynet.service;

import com.safetynet.dto.floodStations.FloodStationsResponseDto;
import com.safetynet.dto.floodStations.PersonResponseDto;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.mapper.FloodStationsMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class FloodStationsService {

    private final FireStationRepository fireStationRepository;

    private final PersonRepository personRepository;

    private final FloodStationsMapper floodStationsMapper;

    public FloodStationsResponseDto getFloodStationsDto(List<Integer> stations) {
        List<FireStation> fireStations = fireStationRepository.findByStations(stations);
        List<Person> people = personRepository.findByFireStations(fireStations);
        Map<String, List<PersonResponseDto>> homes = people.stream()
                .collect(groupingBy(
                        Person::getAddress,
                        mapping(floodStationsMapper::toPersonResponseDto, toList())
                ));
        return FloodStationsResponseDto.builder()
                .homes(homes)
                .build();
    }
}
