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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(
            @RequestBody CreatePersonRequestDto requestDto
    ) {
        log.info("POST /persons - {}", requestDto);
        Person person = personService.createPerson(requestDto);
        PersonResponseDto responseDto = personMapper.toResponseDto(person);
        log.info("POST /persons - 201 Created - {}", responseDto);
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<PersonResponseDto> updatePerson(
            @RequestBody UpdatePersonRequestDto requestDto
    ) {
        log.info("PUT /persons - {}", requestDto);
        Person person = personService.updatePerson(requestDto);
        PersonResponseDto responseDto = personMapper.toResponseDto(person);
        log.info("PUT /persons - 200 OK - {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(
            @RequestBody DeletePersonRequestDto requestDto
    ) {
        log.info("DELETE /persons - {}", requestDto);
        personService.deletePerson(requestDto);
        log.info("DELETE /persons - 204 No Content");
        return ResponseEntity.noContent().build();
    }
}
