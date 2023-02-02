package com.safetynet.IT.controller.fireStation;

import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class DeleteFireStationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testDeletePerson() throws Exception {
        // Given
        int sizeBefore = personRepository.findAll().size();
        // When
        mockMvc.perform(delete("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "firstName": "John",
                          "lastName": "Boyd"
                          }
                        """)).andExpect(status().isNoContent());
        // Then
        int sizeAfter = personRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore - 1);
        Optional<Person> person = personRepository.findByFirstNameAndLastName("John", "Boyd");
        assertThat(person).isEmpty();
    }


}
