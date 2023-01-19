package com.safetynet.service;

import com.safetynet.dto.phoneAlert.PhoneAlertResponseDto;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneAlertService {

    private final FireStationRepository fireStationRepository;

    private final PersonRepository personRepository;

    public PhoneAlertResponseDto getPhoneAlertResponseDto(Integer station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        if (fireStations.isEmpty()) throw new NoSuchFireStationException(station);
        List<Person> people = personRepository.findByFireStations(fireStations);
        List<String> phones = people
                .stream()
                .map(Person::getPhone)
                .distinct()
                .toList();
        return PhoneAlertResponseDto.builder()
                .phones(phones)
                .build();
    }

}
