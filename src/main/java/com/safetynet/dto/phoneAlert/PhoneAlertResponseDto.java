package com.safetynet.dto.phoneAlert;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PhoneAlertResponseDto {

    List<String> phones;

}
