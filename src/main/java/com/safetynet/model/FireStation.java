package com.safetynet.model;

import lombok.*;
import lombok.Data;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FireStation {

    private String address;

    private Integer station;

    private List<Person> people;

}