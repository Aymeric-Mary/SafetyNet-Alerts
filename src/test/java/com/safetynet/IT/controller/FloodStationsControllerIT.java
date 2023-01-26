package com.safetynet.IT.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FloodStationsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private RequestBuilder getRequestBuilder(List<Integer> stations) {
        return get("/flood/stations?stations=" + StringUtils.join(stations, ","));
    }

    @Test
    void getPersonByListOfStations_with2Stations() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(List.of(2, 4)))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String epectedJson = """
                {
                  "homes": {
                    "489 Manchester St": [
                      {
                        "firstName": "Lily",
                        "lastName": "Cooper",
                        "phone": "841-874-9845",
                        "age": 28,
                        "medications": [],
                        "allergies": []
                      }
                    ],
                    "892 Downing Ct": [
                      {
                        "firstName": "Sophia",
                        "lastName": "Zemicks",
                        "phone": "841-874-7878",
                        "age": 34,
                        "medications": [
                          "aznol:60mg",
                          "hydrapermazol:900mg",
                          "pharmacol:5000mg",
                          "terazine:500mg"
                        ],
                        "allergies": [
                          "peanut",
                          "shellfish",
                          "aznol"
                        ]
                      },
                      {
                        "firstName": "Warren",
                        "lastName": "Zemicks",
                        "phone": "841-874-7512",
                        "age": 37,
                        "medications": [],
                        "allergies": []
                      },
                      {
                        "firstName": "Zach",
                        "lastName": "Zemicks",
                        "phone": "841-874-7512",
                        "age": 5,
                        "medications": [],
                        "allergies": []
                      }
                    ],
                    "29 15th St": [
                      {
                        "firstName": "Jonanathan",
                        "lastName": "Marrack",
                        "phone": "841-874-6513",
                        "age": 34,
                        "medications": [],
                        "allergies": []
                      }
                    ]
                  }
                }
                """;
        JSONAssert.assertEquals(epectedJson, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPersonByListOfStations_with1Station() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(List.of(1)))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String epectedJson = """
                {
                  "homes": {
                    "947 E. Rose Dr": [
                      {
                        "firstName": "Brian",
                        "lastName": "Stelzer",
                        "phone": "841-874-7784",
                        "age": 47,
                        "medications": [
                          "ibupurin:200mg",
                          "hydrapermazol:400mg"
                        ],
                        "allergies": [
                          "nillacilan"
                        ]
                      },
                      {
                        "firstName": "Shawna",
                        "lastName": "Stelzer",
                        "phone": "841-874-7784",
                        "age": 42,
                        "medications": [],
                        "allergies": []
                      },
                      {
                        "firstName": "Kendrik",
                        "lastName": "Stelzer",
                        "phone": "841-874-7784",
                        "age": 8,
                        "medications": [
                          "noxidian:100mg",
                          "pharmacol:2500mg"
                        ],
                        "allergies": []
                      }
                    ],
                    "908 73rd St": [
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
                    "644 Gershwin Cir": [
                      {
                        "firstName": "Peter",
                        "lastName": "Duncan",
                        "phone": "841-874-6512",
                        "age": 22,
                        "medications": [],
                        "allergies": [
                          "shellfish"
                        ]
                      }
                    ]
                  }
                }
                """;
        JSONAssert.assertEquals(epectedJson, result.getResponse().getContentAsString(), true);
    }

    @Test
    void getPersonByListOfStations_withNoPerson() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(getRequestBuilder(List.of(5)))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        String epectedJson = """
                {
                    "homes":{}
                }
                """;
        JSONAssert.assertEquals(epectedJson, result.getResponse().getContentAsString(), true);
    }
}
