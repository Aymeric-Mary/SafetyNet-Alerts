package com.safetynet.IT.controller.medicalRecord;

import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PutMedicalRecordTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void testPutMedicalRecord() throws Exception {
        // Given
        int sizeBefore = medicalRecordRepository.findAll().size();
        // When
        String result = mockMvc.perform(put("/medicalRecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "firstName": "John",
                                  "lastName": "Boyd",
                                  "birthdate": "03/06/1988",
                                  "medications": [
                                    "aznol:350mg",
                                    "hydrapermazol:100mg"
                                  ],
                                  "allergies": [
                                    "nillacilan",
                                    "peanut"
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Then
        int sizeAfter = medicalRecordRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore);
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Boyd");
        assertThat(medicalRecord).isPresent();
        Person person = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("jaboyd@email.com")
                .build();
        MedicalRecord expected = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .birthdate("03/06/1988")
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan", "peanut"))
                .person(person)
                .build();
        assertThat(medicalRecord.get())
                .usingRecursiveComparison()
                .ignoringFields("person.medicalRecord", "person.fireStation")
                .isEqualTo(expected);
        JSONAssert.assertEquals("""
                {
                  "firstName": "John",
                  "lastName": "Boyd",
                  "birthdate": "03/06/1988",
                  "medications": [
                    "aznol:350mg",
                    "hydrapermazol:100mg"
                  ],
                  "allergies": [
                    "nillacilan",
                    "peanut"
                  ]
                }
                """, result, true);
    }

}