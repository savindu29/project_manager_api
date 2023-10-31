package com.inova.project_manager_api.dto;

import com.inova.project_manager_api.dto.response.ErrorResponseDto;
import com.inova.project_manager_api.dto.response.MetaDataResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> {
    private T data;

    private MetaDataResponseDto meta;

    private ErrorResponseDto error;

    private static final Integer STATUS_CODE_SUCCESS = 1;
    private static final String STATUS_SUCCESS = "Success";
    private static final Integer STATUS_CODE_ERROR = 0;
    private static final String STATUS_ERROR = "Error";

    public static <T> AppResponse<T> error(String status, String source, String title, String detail) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
                .build();

        ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status).source(source).title(title).detail(detail)
                .build();

        return AppResponse.<T>builder().data(null).meta(metaDto).error(errorDto).build();
    }

    public AppResponse<T> error(ErrorResponseDto error) {
        this.error = error;
        return this;
    }

    public static <T> AppResponse<T> ok(T data) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS).message(STATUS_SUCCESS)
                .build();
        return AppResponse.<T>builder().data(data).meta(metaDto).build();
    }
}
