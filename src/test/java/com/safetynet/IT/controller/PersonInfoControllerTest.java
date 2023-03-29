package com.safetynet.IT.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPersonInfoByFirstNameAndLastName() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(get("/personInfo?firstName=Tenley&lastName=Boyd"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "people": [
                      {
                        "firstName": "Tenley",
                        "lastName": "Boyd",
                        "address": "1509 Culver St",
                        "age": 11,
                        "email": "tenz@email.com",
                        "medications": [],
                        "allergies": [
                          "peanut"
                        ]
                      }
                    ]
                  }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPersonInfoByLastName() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(get("/personInfo?lastName=Boyd"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                     "people": [
                       {
                         "firstName": "John",
                         "lastName": "Boyd",
                         "address": "1509 Culver St",
                         "age": 39,
                         "email": "jaboyd@email.com",
                         "medications": [
                           "aznol:350mg",
                           "hydrapermazol:100mg"
                         ],
                         "allergies": [
                           "nillacilan"
                         ]
                       },
                       {
                         "firstName": "Jacob",
                         "lastName": "Boyd",
                         "address": "1509 Culver St",
                         "age": 34,
                         "email": "drk@email.com",
                         "medications": [
                           "pharmacol:5000mg",
                           "terazine:10mg",
                           "noznazol:250mg"
                         ],
                         "allergies": []
                       },
                       {
                         "firstName": "Tenley",
                         "lastName": "Boyd",
                         "address": "1509 Culver St",
                         "age": 11,
                         "email": "tenz@email.com",
                         "medications": [],
                         "allergies": [
                           "peanut"
                         ]
                       },
                       {
                         "firstName": "Roger",
                         "lastName": "Boyd",
                         "address": "1509 Culver St",
                         "age": 5,
                         "email": "jaboyd@email.com",
                         "medications": [],
                         "allergies": []
                       },
                       {
                         "firstName": "Felicia",
                         "lastName": "Boyd",
                         "address": "1509 Culver St",
                         "age": 37,
                         "email": "jaboyd@email.com",
                         "medications": [
                           "tetracyclaz:650mg"
                         ],
                         "allergies": [
                           "xilliathal"
                         ]
                       },
                       {
                         "firstName": "Allison",
                         "lastName": "Boyd",
                         "address": "112 Steppes Pl",
                         "age": 58,
                         "email": "aly@imail.com",
                         "medications": [
                           "aznol:200mg"
                         ],
                         "allergies": [
                           "nillacilan"
                         ]
                       }
                     ]
                   }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPersonInfoByFirstName() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(get("/personInfo?firstName=Foster"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                     "people": [
                       {
                         "firstName": "Foster",
                         "lastName": "Shepard",
                         "address": "748 Townings Dr",
                         "age": 43,
                         "email": "jaboyd@email.com",
                         "medications": [],
                         "allergies": []
                       }
                     ]
                   }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void souldThrowIllegalArgument_whenFirstNameAndLastNameAreNull() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(get("/personInfo"))
                .andExpect(status().isBadRequest())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "error":"ILLEGAL_ARGUMENT",
                    "message":"firstName or lastName is mandatory"
                }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

}
