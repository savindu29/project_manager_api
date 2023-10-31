package com.inova.project_manager_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MetaDataResponseDto {
    private Integer code;
    private String message;
}
