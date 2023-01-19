package com.safetynet.IT.repository;

import com.safetynet.model.FireStation;
import com.safetynet.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FireStationRepositoryTest {

    private final FireStationRepository fireStationRepository = new FireStationRepository();

    @Test
    void findAllTest() {
        // Given
        // When
        List<FireStation> fireStations = fireStationRepository.findAll();
        // Then
        assertThat(fireStations.size()).isEqualTo(12);
        assertThat(fireStations)
                .flatExtracting("address", "station", "people")
                .doesNotContainNull();
    }

    @ParameterizedTest
    @CsvSource({
            "1,3",
            "2,3",
            "3,4",
            "4,2",
            "5,0"
    })
    void findByStationsTest(Integer station, Integer expectedSize) {
        // Given
        // When
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        // Then
        assertThat(fireStations.size()).isEqualTo(expectedSize);
        assertThat(fireStations).allMatch(fireStation -> fireStation.getStation().equals(station));
    }

}