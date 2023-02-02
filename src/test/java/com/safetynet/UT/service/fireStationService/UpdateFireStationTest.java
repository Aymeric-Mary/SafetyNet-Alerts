package com.safetynet.UT.service.fireStationService;

import com.safetynet.dto.fireStation.UpdateFireStationRequestDto;
import com.safetynet.dto.person.UpdatePersonRequestDto;
import com.safetynet.exception.NoSuchFireStationException;
import com.safetynet.exception.NoSuchPersonException;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
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
public class UpdateFireStationTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private FireStationMapper fireStationMapper;

    @InjectMocks
    private FireStationService sut;

    @Test
    void testUpdateFireStation() {
        // Given
        UpdateFireStationRequestDto requestDto = new UpdateFireStationRequestDto(
                "1234 Elm St",
                1);
        FireStation expected = FireStation.builder()
                .address("1234 Elm St")
                .station(1)
                .build();
        when(fireStationRepository.findByAddress("1234 Elm St")).thenReturn(Optional.of(expected));
        // When
        FireStation fireStation = sut.updateFireStation(requestDto);
        // Then
        assertThat(fireStation).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testUpdateFireStationWithNoFireStation() {
        // Given
        UpdateFireStationRequestDto requestDto = new UpdateFireStationRequestDto(
                "1234 Elm St",
                1);
        when(fireStationRepository.findByAddress("1234 Elm St")).thenReturn(Optional.empty());
        // When
        ThrowingCallable throwingCallable = () -> sut.updateFireStation(requestDto);
        // Then
        assertThatThrownBy(throwingCallable).isInstanceOf(NoSuchFireStationException.class);
    }
}
