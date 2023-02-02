package com.safetynet.IT.controller.person;

import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.MedicalRecordRepository;
import com.safetynet.repository.PersonRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PutPersonTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testPutPerson() throws Exception {
        // Given
        int sizeBefore = personRepository.findAll().size();
        // When
        String result = mockMvc.perform(put("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "firstName": "John",
                          "lastName": "Boyd",
                          "address": "1509 Culver St",
                          "city": "Culver",
                          "zip": "97451",
                          "phone": "841-874-6512",
                          "email": "new-email@email.com"
                          }
                        """)).andReturn().getResponse().getContentAsString();
        // Then
        int sizeAfter = personRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore);
        Optional<Person> person = personRepository.findByFirstNameAndLastName("John", "Boyd");
        assertThat(person).isNotEmpty();
        Person expectedPerson = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("new-email@email.com")
                .build();
        assertThat(person.get()).usingRecursiveComparison().ignoringFields("fireStation", "medicalRecord").isEqualTo(expectedPerson);
        JSONAssert.assertEquals(result, """
                {
                  "firstName": "John",
                  "lastName": "Boyd",
                  "address": "1509 Culver St",
                  "city": "Culver",
                  "zip": "97451",
                  "phone": "841-874-6512",
                  "email": "new-email@email.com"
                  }
                """, true);
    }

}