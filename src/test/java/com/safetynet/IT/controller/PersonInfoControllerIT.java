package com.safetynet.IT.controller;

import com.safetynet.controller.PersonInfoController;
import com.safetynet.dto.personInfo.PersonInfoResponseDto;
import com.safetynet.dto.personInfo.PersonResponseDto;
import com.safetynet.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonInfoController.class)
public class PersonInfoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonInfoService personInfoService;

    @Test
    void testGetPersonInfo() throws Exception {
        // Given
        PersonInfoResponseDto expected = new PersonInfoResponseDto(
                List.of(
                        PersonResponseDto.builder()
                                .firstName("John")
                                .lastName("Boyd")
                                .address("1509 Culver St")
                                .age(36)
                                .email("jaboyd@email.com")
                                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                                .allergies(List.of("nillacilan"))
                                .build()
                )
        );
        when(personInfoService.getPersonInfoResponseDto("John", "Boyd")).thenReturn(expected);
        // When
        String result = mockMvc.perform(get("/personInfo")
                        .param("firstName", "John")
                        .param("lastName", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Then
        JSONAssert.assertEquals("""
                {
                  "people": [
                    {
                      "firstName": "John",
                      "lastName": "Boyd",
                      "address": "1509 Culver St",
                      "age": 36,
                      "email": "jaboyd@email.com",
                      "medications": [
                        "aznol:350mg",
                        "hydrapermazol:100mg"
                      ],
                      "allergies": [
                        "nillacilan"
                      ]
                    }
                  ]
                }
                """, result, true);
    }
}
