package com.safetynet.dto.floodStations;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class FloodStationsResponseDto {

    Map<String, List<PersonResponseDto>> homes;
}
