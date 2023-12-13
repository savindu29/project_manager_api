package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.utils.SearchResponseDtoBuilder;
import org.springframework.stereotype.Component;

@Component
public class DocumentResponseDtoBuilder implements SearchResponseDtoBuilder<DocumentResponse,DocumentResponseDto> {
    @Override
    public DocumentResponseDto buildResponseDto(DocumentResponse response) {
        return DocumentResponseDto.builder()
                .documentReference((response.getEntry()!=null? response.getEntry().getId():null))
                .parentReference((response.getEntry()!=null? response.getEntry().getParentId():null))
                .build();


    }
}
