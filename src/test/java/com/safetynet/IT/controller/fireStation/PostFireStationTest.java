package com.safetynet.IT.controller.fireStation;

import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PostFireStationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FireStationRepository fireStationRepository;

    @Test
    void testPostFireStation() throws Exception {
        // Given
        int sizeBefore = fireStationRepository.findAll().size();
        // When
        mockMvc.perform(post("/firestations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "address": "1234 Elm St",
                          "station": "3"
                          }
                        """)).andExpect(status().isCreated());
        // Then
        int sizeAfter = fireStationRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore + 1);
        Optional<FireStation> fireStation = fireStationRepository.findByAddress("1234 Elm St");
        assertThat(fireStation).isNotEmpty();
        FireStation expected = FireStation.builder()
                .address("1234 Elm St")
                .station(3)
                .build();
        assertThat(fireStation.get()).usingRecursiveComparison().ignoringFields("people").isEqualTo(expected);
    }

}