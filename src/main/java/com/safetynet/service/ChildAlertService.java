package com.safetynet.service;

import com.safetynet.dto.childAlert.ChildResponseDto;
import com.safetynet.dto.childAlert.ChildAlertResponseDto;
import com.safetynet.dto.fire.FireResponseDto;
import com.safetynet.dto.fire.PersonResponseDto;
import com.safetynet.exception.NoSuchAddressException;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.mapper.ChildAlertMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildAlertService {

    private final PersonRepository personRepository;

    private final ChildAlertMapper childAlertMapper;

    public ChildAlertResponseDto getChildrenByAddress(String address) {
        List<Person> people = personRepository.findByAddress(address);
        if (people.isEmpty()) throw new NoSuchAddressException(address);
        List<ChildResponseDto> responseDtos = childAlertMapper.toChildrenResponseDtos(people);
        return ChildAlertResponseDto.builder()
                .children(responseDtos)
                .build();
    }

}
