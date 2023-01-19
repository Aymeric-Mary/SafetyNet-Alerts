package com.safetynet.service;

import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.dto.fire.PersonResponseDto;
import com.safetynet.exception.NoSuchAddressException;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.mapper.FireMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireService {

    private final PersonRepository personRepository;

    private final FireStationRepository fireStationRepository;

    private final FireMapper fireMapper;

    public FireResponseDto getFireResponseDto(String address) {
        List<Person> people = personRepository.findByAddress(address);
        if (people.isEmpty()) throw new NoSuchAddressException(address);
        FireStation fireStation = fireStationRepository.findByAdress(address)
                .orElseThrow(() -> new NoSuchFireStationException(address));
        List<PersonResponseDto> responseDtos = fireMapper.toPersonResponseDtos(people);
        return FireResponseDto.builder()
                .people(responseDtos)
                .fireStationNumber(fireStation.getStation())
                .build();
    }
}
