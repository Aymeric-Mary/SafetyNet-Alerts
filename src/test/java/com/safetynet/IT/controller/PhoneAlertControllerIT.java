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
public class PhoneAlertControllerIT {

    @Autowired
    MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(Integer fireStationNumber) {
        return get("/phoneAlert?firestation=" + fireStationNumber);
    }

    @Test
    void phoneAlert() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(3))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                  "phones": [
                    "841-874-6512",
                    "841-874-6513",
                    "841-874-6544",
                    "841-874-6741",
                    "841-874-6874",
                    "841-874-8888",
                    "841-874-9888"
                  ]
                }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPhonesByStation_withNoSuchFireStation() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(5))
                .andExpect(status().isNotFound())
                .andReturn();
        // Then
        JSONAssert.assertEquals("""
                {
                    "error":"NO_SUCH_FIRESTATION",
                    "station":5
                }
                """, result.getResponse().getContentAsString(), true);
    }

}
