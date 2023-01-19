package com.safetynet.service;

import com.safetynet.dto.getChildrenByAddress.ChildrenResponseDto;
import com.safetynet.dto.getChildrenByAddress.GetChildrenByAddressResponseDto;
import com.safetynet.dto.GetPeopleByStationResponseDto;
import com.safetynet.exception.NoSuchAddressException;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
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
        if (fireStations.isEmpty()) throw new NoSuchFireStationException(station);
        List<Person> people = personRepository.findByFireStations(fireStations);
        Integer nbAdults = (int) people.stream().filter(Person::isAdult).count();
        Integer nbChildren = (int) people.stream().filter(Person::isChild).count();
        return GetPeopleByStationResponseDto.builder()
                .people(personMapper.toGetPersonByStationResponseDtos(people))
                .nbAdults(nbAdults)
                .nbChildren(nbChildren)
                .build();
    }

    public GetChildrenByAddressResponseDto getChildrenByAddress(String address) {
        List<Person> people = personRepository.findByAddress(address);
        if (people.isEmpty()) throw new NoSuchAddressException(address);
        List<ChildrenResponseDto> responseDtos = personMapper.toChildrenResponseDtos(people);
        return GetChildrenByAddressResponseDto.builder()
                .children(responseDtos)
                .build();
    }

}