package com.safetynet.UT.repository;

import com.safetynet.model.Data;
import com.safetynet.model.FireStation;
import com.safetynet.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void findByAddressTest_whenFireStationIsPresent() {
        // Given
        // When
        Optional<FireStation> fireStation = fireStationRepository.findByAddress("29 15th St");
        // Then
        assertThat(fireStation).isPresent();
        FireStation expected = FireStation.builder()
                .address("29 15th St")
                .station(2)
                .build();
        assertThat(fireStation.get())
                .usingRecursiveComparison()
                .ignoringFields("people")
                .isEqualTo(expected);
    }

    @Test
    void findByAddressTest_whenFireStationIsNotPresent() {
        // Given
        // When
        Optional<FireStation> fireStation = fireStationRepository.findByAddress("address");
        // Then
        assertThat(fireStation).isNotPresent();
    }

    @Test
    void findByStationsTest_whenFireStationIsNotEmpty() {
        // Given
        // When
        List<FireStation> fireStations = fireStationRepository.findByStations(List.of(1, 2));
        // Then
        assertThat(fireStations.size()).isEqualTo(6);
        assertThat(fireStations)
                .allMatch(fireStation -> List.of(1, 2).contains(fireStation.getStation()));
    }

    @Test
    void findByStationsTest_whenFireStationIsEmpty() {
        // Given
        // When
        List<FireStation> fireStations = fireStationRepository.findByStations(List.of(5));
        // Then
        assertThat(fireStations).isEmpty();
    }

    @Test
    void saveTest_withNewFireStation() {
        // Given
        FireStation fireStation = FireStation.builder()
                .address("address")
                .station(1)
                .build();
        // When
        FireStation result = fireStationRepository.save(fireStation);
        // Then
        Optional<FireStation> actualFireStation = fireStationRepository.findByAddress("address");
        assertThat(actualFireStation).isPresent();
        assertThat(actualFireStation.get())
                .usingRecursiveComparison()
                .ignoringFields("people")
                .isEqualTo(fireStation);
        assertThat(result).isEqualTo(actualFireStation.get());
    }

    @Test
    void saveTest_withExistingFireStation() {
        // Given
        FireStation fireStation = fireStationRepository.findByAddress("29 15th St").get();
        fireStation.setStation(1);
        // When
        FireStation result = fireStationRepository.save(fireStation);
        // Then
        FireStation actualFireStation = fireStationRepository.findByAddress("29 15th St").get();
        assertThat(actualFireStation.getStation()).isEqualTo(1);
        assertThat(result).isEqualTo(fireStation);
    }

    @Test
    void deleteTest(){
        // Given
        FireStation fireStation = fireStationRepository.findByAddress("29 15th St").get();
        // When
        fireStationRepository.delete(fireStation);
        // Then
        List<FireStation> fireStations = fireStationRepository.findAll();
        assertThat(fireStations).doesNotContain(fireStation);
    }

}