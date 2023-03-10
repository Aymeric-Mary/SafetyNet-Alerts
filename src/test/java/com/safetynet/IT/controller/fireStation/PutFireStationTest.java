package com.safetynet.IT.controller.fireStation;

import com.safetynet.model.FireStation;
import com.safetynet.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PutFireStationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FireStationRepository fireStationRepository;

    @Test
    void testPutPerson() throws Exception {
        // Given
        int sizeBefore = fireStationRepository.findAll().size();
        // When
        mockMvc.perform(put("/firestations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "address": "1509 Culver St",
                          "station": "2"
                          }
                        """)).andExpect(status().isOk());
        // Then
        int sizeAfter = fireStationRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore);
        Optional<FireStation> fireStation = fireStationRepository.findByAddress("1509 Culver St");
        assertThat(fireStation).isNotEmpty();
        FireStation expected = FireStation.builder()
                .address("1509 Culver St")
                .station(2)
                .build();
        assertThat(fireStation.get()).usingRecursiveComparison().ignoringFields("people").isEqualTo(expected);
    }

}