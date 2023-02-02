package com.safetynet.IT.controller.medicalRecord;

import com.safetynet.model.MedicalRecord;
import com.safetynet.repository.MedicalRecordRepository;
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
public class DeleteMedicalRecordTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void testDeleteMedicalRecord() throws Exception {
        // Given
        int sizeBefore = medicalRecordRepository.findAll().size();
        // When
        String result = mockMvc.perform(delete("/medicalRecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "firstName": "John",
                                  "lastName": "Boyd"
                                }
                                """))
                .andExpect(status().isNoContent())
                .andReturn().getResponse().getContentAsString();
        // Then
        int sizeAfter = medicalRecordRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore - 1);
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Boyd");
        assertThat(medicalRecord).isEmpty();

        assertThat(result).isEqualTo("");
    }


}
