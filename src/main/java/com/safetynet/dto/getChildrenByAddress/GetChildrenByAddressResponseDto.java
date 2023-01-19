package com.safetynet.dto.getChildrenByAddress;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetChildrenByAddressResponseDto {

    private List<ChildrenResponseDto> children;

}
