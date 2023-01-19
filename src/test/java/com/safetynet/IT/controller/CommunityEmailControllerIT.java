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
public class CommunityEmailControllerIT {

    @Autowired
    MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(String city) {
        return get("/communityEmail?city=" + city);
    }

    @Test
    void getEmailsByCity_withSeveralPerson() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("Culver"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "emails": [
                      "jaboyd@email.com",
                      "drk@email.com",
                      "tenz@email.com",
                      "tcoop@ymail.com",
                      "lily@email.com",
                      "soph@email.com",
                      "ward@email.com",
                      "zarc@email.com",
                      "reg@email.com",
                      "jpeter@email.com",
                      "aly@imail.com",
                      "bstel@email.com",
                      "ssanw@email.com",
                      "clivfd@ymail.com",
                      "gramps@email.com"
                    ]
                  }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getChildrenByAddress_with0Person() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("644 Gershwin Cir"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                    "emails": []
                  }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

}
