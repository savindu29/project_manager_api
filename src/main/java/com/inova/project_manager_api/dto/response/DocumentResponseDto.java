package com.inova.project_manager_api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentResponseDto {
    private String parentReference;
    private String documentReference;
    private String exceptionMessage;
    private String exceptionCause;
    private Long totalRecords;
    private List<String> documentReferenceList;


}
