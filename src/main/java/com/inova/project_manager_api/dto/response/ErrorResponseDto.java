package com.inova.project_manager_api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String status;
    private String source;
    private String title;
    private String detail;
}
