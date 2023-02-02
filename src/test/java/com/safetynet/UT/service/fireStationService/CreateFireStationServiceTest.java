package com.safetynet.UT.service.fireStationService;

import com.safetynet.dto.fireStation.CreateFireStationRequestDto;
import com.safetynet.dto.person.CreatePersonRequestDto;
import com.safetynet.exception.FireStationAlreadyExistException;
import com.safetynet.exception.PersonAlreadyExistException;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.service.FireStationService;
import com.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateFireStationServiceTest {

    @Mock
    private FireStationMapper fireStationMapper;

    @Mock
    private FireStationRepository fireStationRepository;

    @InjectMocks
    private FireStationService sut;

    @Test
    void testCreateFireStation() {
        // Given
        CreateFireStationRequestDto requestDto = new CreateFireStationRequestDto(
                "1234 Elm St",
                1);
        FireStation expected = FireStation.builder()
                .address("1234 Elm St")
                .station(1)
                .build();
        when(fireStationRepository.findByAddress("1234 Elm St")).thenReturn(Optional.empty());
        when(fireStationMapper.toFireStation(requestDto)).thenReturn(expected);
        // When
        FireStation fireStation = sut.createFireStation(requestDto);
        // Then
        assertThat(fireStation).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testCreateFireStation_thenThrowFireStationAlreadyExistException() {
        // Given
        CreateFireStationRequestDto requestDto = new CreateFireStationRequestDto(
                "1234 Elm St",
                1);
        FireStation fireStation = FireStation.builder()
                .address("1234 Elm St")
                .build();
        when(fireStationRepository.findByAddress("1234 Elm St")).thenReturn(Optional.of(fireStation));
        // When
        ThrowingCallable callable = () -> sut.createFireStation(requestDto);
        // Then
        assertThatThrownBy(callable).isInstanceOf(FireStationAlreadyExistException.class);
    }

}
