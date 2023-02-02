package com.safetynet.controller;

import com.safetynet.dto.person.CreatePersonRequestDto;
import com.safetynet.dto.person.DeletePersonRequestDto;
import com.safetynet.dto.person.PersonResponseDto;
import com.safetynet.dto.person.UpdatePersonRequestDto;
import com.safetynet.mapper.PersonInfoMapper;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(
            @RequestBody CreatePersonRequestDto requestDto
    ) {
        Person person = personService.createPerson(requestDto);
        PersonResponseDto responseDto = personMapper.toResponseDto(person);
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<PersonResponseDto> updatePerson(
            @RequestBody UpdatePersonRequestDto requestDto
    ) {
        Person person = personService.updatePerson(requestDto);
        PersonResponseDto responseDto = personMapper.toResponseDto(person);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(
            @RequestBody DeletePersonRequestDto requestDto
    ) {
        personService.deletePerson(requestDto);
        return ResponseEntity.noContent().build();
    }
}
