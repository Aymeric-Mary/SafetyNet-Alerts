package com.safetynet.service;

import com.safetynet.dto.communityEmail.CommunityEmailResponseDto;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityEmailService {

    private final PersonRepository personRepository;

    public CommunityEmailResponseDto getCommunityResponseDto(String city) {
        List<Person> people = personRepository.findByCity(city);
        List<String> emails = people
                .stream()
                .map(Person::getEmail)
                .distinct()
                .toList();
        return CommunityEmailResponseDto.builder()
                .emails(emails)
                .build();
    }

}
