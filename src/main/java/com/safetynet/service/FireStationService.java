package com.safetynet.service;

import com.safetynet.dto.fireStation.FireStationResponseDto;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    private final PersonRepository personRepository;

    private final FireStationMapper fireStationMapper;

    public FireStationResponseDto getByStation(Integer station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        if (fireStations.isEmpty()) throw new NoSuchFireStationException(station);
        List<Person> people = personRepository.findByFireStations(fireStations);
        Integer nbAdults = (int) people.stream().filter(Person::isAdult).count();
        Integer nbChildren = (int) people.stream().filter(Person::isChild).count();
        return FireStationResponseDto.builder()
                .people(fireStationMapper.toPersonResponseDtos(people))
                .nbAdults(nbAdults)
                .nbChildren(nbChildren)
                .build();
    }

}