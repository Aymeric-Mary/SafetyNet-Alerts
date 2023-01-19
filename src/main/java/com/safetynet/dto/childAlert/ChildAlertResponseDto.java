package com.safetynet.dto.childAlert;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ChildAlertResponseDto {

    private List<ChildResponseDto> children;

}
