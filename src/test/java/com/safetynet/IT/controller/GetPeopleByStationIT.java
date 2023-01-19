package com.safetynet.IT.controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPeopleByStationIT {

    @Autowired
    MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(Integer stationNumber) {
        return get("/firestation?stationNumber=" + stationNumber);
    }

    @Test
    void getPeopleByStation_withPeopleNotEmpty() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(1))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                  "people": [
                    {
                      "firstName": "Peter",
                      "lastName": "Duncan",
                      "address": "644 Gershwin Cir",
                      "phone": "841-874-6512"
                    },
                    {
                      "firstName": "Reginold",
                      "lastName": "Walker",
                      "address": "908 73rd St",
                      "phone": "841-874-8547"
                    },
                    {
                      "firstName": "Jamie",
                      "lastName": "Peters",
                      "address": "908 73rd St",
                      "phone": "841-874-7462"
                    },
                    {
                      "firstName": "Brian",
                      "lastName": "Stelzer",
                      "address": "947 E. Rose Dr",
                      "phone": "841-874-7784"
                    },
                    {
                      "firstName": "Shawna",
                      "lastName": "Stelzer",
                      "address": "947 E. Rose Dr",
                      "phone": "841-874-7784"
                    },
                    {
                      "firstName": "Kendrik",
                      "lastName": "Stelzer",
                      "address": "947 E. Rose Dr",
                      "phone": "841-874-7784"
                    }
                  ],
                  "nbAdults": 5,
                  "nbChildren": 1
                }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPeopleByStation_withNoSuchFireStation() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(5))
                .andExpect(status().isNotFound())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "station":5,
                    "error":"NO_SUCH_FIRESTATION"
                }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

}
