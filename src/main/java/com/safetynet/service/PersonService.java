package com.safetynet.service;

import com.safetynet.dto.person.CreatePersonRequestDto;
import com.safetynet.dto.person.DeletePersonRequestDto;
import com.safetynet.dto.person.UpdatePersonRequestDto;
import com.safetynet.exception.NoSuchPersonException;
import com.safetynet.exception.PersonAlreadyExistException;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public Person createPerson(CreatePersonRequestDto requestDto) {
        assertPersonNoExist(requestDto.firstName(), requestDto.lastName());
        Person person = personMapper.toPerson(requestDto);
        personRepository.save(person);
        return person;
    }

    public Person updatePerson(UpdatePersonRequestDto requestDto) {
        Person person = getPerson(requestDto.firstName(), requestDto.lastName());
        personMapper.mapUpdateRequestDto(person, requestDto);
        personRepository.save(person);
        return person;
    }

    public void deletePerson(DeletePersonRequestDto requestDto) {
        personRepository.findByFirstNameAndLastName(requestDto.firstName(), requestDto.lastName())
                .ifPresent(personRepository::delete);
    }

    private Person getPerson(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new NoSuchPersonException(firstName, lastName));
    }

    private void assertPersonNoExist(String firstName, String lastName) {
        personRepository.findByFirstNameAndLastName(firstName, lastName)
                .ifPresent(person -> {
                    throw new PersonAlreadyExistException(firstName, lastName);
                });
    }
}
