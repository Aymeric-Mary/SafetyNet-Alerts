package com.safetynet.dto.phoneAlert;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PhoneAlertResponseDto {

    List<String> phones;

}
