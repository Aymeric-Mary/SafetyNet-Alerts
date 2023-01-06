package com.safetynet.alerts.model;

import lombok.*;
import lombok.Data;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FireStation {

    private String address;

    private Integer station;

    private List<Person> persons;

}