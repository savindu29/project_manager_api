package com.inova.project_manager_api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataResponseDto {
    private Integer code;
    private String message;
}
