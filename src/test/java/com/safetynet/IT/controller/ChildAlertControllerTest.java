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
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ChildAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(String address) {
        return get("/childAlert?address=" + address);
    }

    @Test
    void getChildrenByAddress_with2Children() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("1509 Culver St"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String jsonExpected = """
                {
                   "children": [
                     {
                       "firstName": "Tenley",
                       "lastName": "Boyd",
                       "age": 10,
                       "otherMembers": [
                         {
                           "firstName": "John",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Jacob",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Roger",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Felicia",
                           "lastName": "Boyd"
                         }
                       ]
                     },
                     {
                       "firstName": "Roger",
                       "lastName": "Boyd",
                       "age": 5,
                       "otherMembers": [
                         {
                           "firstName": "John",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Jacob",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Tenley",
                           "lastName": "Boyd"
                         },
                         {
                           "firstName": "Felicia",
                           "lastName": "Boyd"
                         }
                       ]
                     }
                   ]
                 }
                """;
        JSONAssert.assertEquals(jsonExpected, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getChildrenByAddress_with0Child() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder("644 Gershwin Cir"))
                .andExpect(status().isNoContent())
                .andReturn();
        // Then
        assertThat(result.getResponse().getContentAsString()).isEqualTo("");
    }

    @Test
    void getChildrenByAddress_withNoSuchAddress() throws Exception {
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
