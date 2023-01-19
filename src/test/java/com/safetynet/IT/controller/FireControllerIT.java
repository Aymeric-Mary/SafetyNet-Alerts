package com.safetynet.IT.controller;

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
public class FireControllerIT {

    @Autowired
    MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(String address) {
        return get("/fire?address=" + address);
    }

    @Test
    void getPeopleByAddress() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("908 73rd St"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "people": [
                      {
                        "firstName": "Reginold",
                        "lastName": "Walker",
                        "phone": "841-874-8547",
                        "age": 43,
                        "medications": [
                          "thradox:700mg"
                        ],
                        "allergies": [
                          "illisoxian"
                        ]
                      },
                      {
                        "firstName": "Jamie",
                        "lastName": "Peters",
                        "phone": "841-874-7462",
                        "age": 40,
                        "medications": [],
                        "allergies": []
                      }
                    ],
                    "fireStationNumber": 1
                  }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPeopleByAddress_withNoSuchFireStation() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("951 new Street"))
                .andExpect(status().isNotFound())
                .andReturn();
        // Then
        JSONAssert.assertEquals("""
                {
                    "error":"NO_SUCH_FIRESTATION",
                    "address":"951 new Street"
                }
                """, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPeopleByAddress_withNoSuchAddress() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("invalid address"))
                .andExpect(status().isNotFound())
                .andReturn();
        // Then
        JSONAssert.assertEquals("""
                {
                    "error":"NO_SUCH_ADDRESS",
                    "address":"invalid address"
                }
                """, result.getResponse().getContentAsString(), true);
    }

}
