package com.safetynet.service;

import com.safetynet.dto.medicalRecord.DeleteMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.exception.MedicalRecordAlreadyExistException;
import com.safetynet.exception.NoSuchMedicalRecordException;
import com.safetynet.exception.NoSuchPersonException;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.MedicalRecordRepository;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordMapper medicalRecordMapper;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonRepository personRepository;

    public MedicalRecord createMedicalRecord(CreateMedicalRecordRequestDto requestDto) {
        Person person = personRepository.findByFirstNameAndLastName(requestDto.firstName(), requestDto.lastName())
                .orElseThrow(() -> new NoSuchPersonException(requestDto.firstName(), requestDto.lastName()));
        assertMedicalRecordNoExist(requestDto.firstName(), requestDto.lastName());
        MedicalRecord medicalRecord = medicalRecordMapper.toMedicalRecord(requestDto, person);
        medicalRecordRepository.save(medicalRecord);
        return medicalRecord;
    }


    public MedicalRecord updateMedicalRecord(UpdateMedicalRecordRequestDto requestDto) {
        MedicalRecord medicalRecord = getMedicalRecord(requestDto.getFirstName(), requestDto.getLastName());
        medicalRecordMapper.mapUpdateRequestDto(medicalRecord, requestDto);
        medicalRecordRepository.save(medicalRecord);
        return medicalRecord;
    }

    public void deleteMedicalRecord(DeleteMedicalRecordRequestDto requestDto) {
        medicalRecordRepository.findByFirstNameAndLastName(requestDto.firstName(), requestDto.lastName())
                .ifPresent(medicalRecordRepository::delete);
    }

    private MedicalRecord getMedicalRecord(String firstName, String lastName) {
        return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new NoSuchMedicalRecordException(firstName, lastName));
    }

    private void assertMedicalRecordNoExist(String firstName, String lastName) {
        medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName)
                .ifPresent(medicalRecord -> {
                    throw new MedicalRecordAlreadyExistException(firstName, lastName);
                });
    }
}
