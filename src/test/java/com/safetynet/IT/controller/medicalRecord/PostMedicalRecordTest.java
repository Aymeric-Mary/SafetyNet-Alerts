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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PostMedicalRecordTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void testPostMedicalRecord() throws Exception {
        // Given
        int sizeBefore = medicalRecordRepository.findAll().size();
        // When
        String result = mockMvc.perform(post("/medicalRecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "firstName": "Emily",
                                  "lastName": "Smith",
                                  "birthdate": "03/06/1988",
                                  "medications": [
                                    "ibupurin:200mg",
                                    "hydrapermazol:400mg"
                                  ],
                                  "allergies": [
                                    "shellfish"
                                  ]
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        // Then
        int sizeAfter = medicalRecordRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore + 1);
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("Emily", "Smith");
        assertThat(medicalRecord).isPresent();
        Person person = Person.builder()
                .firstName("Emily")
                .lastName("Smith")
                .address("1234 Elm St")
                .city("Culver")
                .email("emsmith@email.com")
                .phone("841-555-1234")
                .zip("97451")
                .build();
        MedicalRecord expected = MedicalRecord.builder()
                .firstName("Emily")
                .lastName("Smith")
                .birthdate("03/06/1988")
                .medications(List.of("ibupurin:200mg", "hydrapermazol:400mg"))
                .allergies(List.of("shellfish"))
                .person(person)
                .build();
        assertThat(medicalRecord.get())
                .usingRecursiveComparison()
                .ignoringFields("person.medicalRecord", "person.fireStation")
                .isEqualTo(expected);

        JSONAssert.assertEquals("""
                {
                  "firstName": "Emily",
                  "lastName": "Smith",
                  "birthdate": "03/06/1988",
                  "medications": [
                    "ibupurin:200mg",
                    "hydrapermazol:400mg"
                  ],
                  "allergies": [
                    "shellfish"
                  ]
                }
                """, result, true);
    }

}