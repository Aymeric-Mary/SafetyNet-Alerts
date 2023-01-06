package com.safetynet.alerts.UT.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
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
        assertThat(fireStations.size()).isEqualTo(13);
        assertThat(fireStations)
                .flatExtracting("address", "station","persons")
                .doesNotContainNull();
    }

}