package com.safetynet.service;

import com.safetynet.dto.fireStation.CreateFireStationRequestDto;
import com.safetynet.dto.fireStation.DeleteFireStationRequestDto;
import com.safetynet.dto.fireStation.GetFireStationResponseDto;
import com.safetynet.dto.fireStation.UpdateFireStationRequestDto;
import com.safetynet.exception.FireStationAlreadyExistException;
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

    public GetFireStationResponseDto getByStation(Integer station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        if (fireStations.isEmpty()) throw new NoSuchFireStationException(station);
        List<Person> people = personRepository.findByFireStations(fireStations);
        Integer nbAdults = (int) people.stream().filter(Person::isAdult).count();
        Integer nbChildren = (int) people.stream().filter(Person::isChild).count();
        return GetFireStationResponseDto.builder()
                .people(fireStationMapper.toPersonResponseDtos(people))
                .nbAdults(nbAdults)
                .nbChildren(nbChildren)
                .build();
    }

    public FireStation createFireStation(CreateFireStationRequestDto createFireStationRequestDto) {
        assertFireStationNotExist(createFireStationRequestDto.address());
        FireStation fireStation = fireStationMapper.toFireStation(createFireStationRequestDto);
        fireStationRepository.save(fireStation);
        return fireStation;
    }

    public FireStation updateFireStation(UpdateFireStationRequestDto createFireStationRequestDto) {
        FireStation fireStation = fireStationRepository.findByAddress(createFireStationRequestDto.address())
                .orElseThrow(() -> new NoSuchFireStationException(createFireStationRequestDto.address()));
        fireStation.setStation(createFireStationRequestDto.station());
        return fireStation;
    }

    public void deleteFireStation(DeleteFireStationRequestDto requestDto) {
        fireStationRepository.findByAddress(requestDto.address())
                .ifPresent(fireStationRepository::delete);
    }

    private void assertFireStationNotExist(String address) {
        fireStationRepository.findByAddress(address).ifPresent(fireStation -> {
            throw new FireStationAlreadyExistException(fireStation.getAddress());
        });
    }

}