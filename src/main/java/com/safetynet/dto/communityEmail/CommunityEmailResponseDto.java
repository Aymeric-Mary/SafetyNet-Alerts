package com.safetynet.dto.communityEmail;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class CommunityEmailResponseDto {

    List<String> emails;
}
