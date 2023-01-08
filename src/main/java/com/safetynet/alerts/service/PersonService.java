package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.GetPeopleByStationResponseDto;
import com.safetynet.alerts.exception.NoSuchFireStationException;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final FireStationRepository fireStationRepository;

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public GetPeopleByStationResponseDto getByStation(Integer station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        if(fireStations.isEmpty()) throw new NoSuchFireStationException(station);
        List<Person> people = personRepository.findByFireStations(fireStations);
        Integer nbAdults = (int) people.stream().filter(Person::isAdult).count();
        Integer nbChildren = (int) people.stream().filter(Person::isChild).count();
        return GetPeopleByStationResponseDto.builder()
                .people(personMapper.toGetPersonByStationResponseDtos(people))
                .nbAdults(nbAdults)
                .nbChildren(nbChildren)
                .build();
    }

}