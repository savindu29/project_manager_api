package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.RfpResourceRequestDto;
import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.RfpResource;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RfpResourceMapper {
    public RfpResourceResponseDto toRfpResourceResponseDto(RfpResource rfpResource) {
        if(rfpResource==null){
            return null;
        }
        return new RfpResourceResponseDto(
                rfpResource.getId(),  rfpResource.getDocumentReference(),rfpResource.getDescription(),rfpResource.getFileType()
        );
    }
    public List<RfpResourceResponseDto> rfpResourceResponseDtoList (List<RfpResource> list){
        if(list==null)return null;
        List<RfpResourceResponseDto> dtoList = new ArrayList<>();
        if(list.size()==0)return dtoList;
        for (RfpResource dto:list) {
            dtoList.add(toRfpResourceResponseDto(dto));
        }
        return dtoList;
    }


    public RfpResource toRfpResourceEntity(RfpResourceRequestDto dto, Project p) {
        RfpResource r = new RfpResource();
        r.setDocumentReference(dto.getDocumentReference());
        r.setDescription(dto.getDescription());
        r.setProject(p);

        return r;
    }

}
