package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.OutputsFromInovaRequestDto;
import com.inova.project_manager_api.dto.response.OutputsFromInovaResponseDto;
import com.inova.project_manager_api.entities.OutputsFromInova;
import com.inova.project_manager_api.entities.Project;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class OutputsFromInovaMapper {
    public OutputsFromInovaResponseDto toOutputsFromInovaResponseDto(OutputsFromInova out) {
        if(out==null){
            return null;
        }
        return new OutputsFromInovaResponseDto(
                out.getId(),  out.getDocumentReference(),out.getDescription(), out.getFileType()
        );
    }
    public List<OutputsFromInovaResponseDto> toOutputsFromInovaResponseDtoList (List<OutputsFromInova> list){
        if(list==null)return null;
        List<OutputsFromInovaResponseDto> dtoList = new ArrayList<>();
        if(list.size()==0)return dtoList;
        for (OutputsFromInova out:list) {
            dtoList.add(toOutputsFromInovaResponseDto(out));
        }
        return dtoList;
    }


    public OutputsFromInova toOutputsFromInovaEntity(OutputsFromInovaRequestDto dto, Project p) {
        OutputsFromInova r = new OutputsFromInova();
        r.setDocumentReference(dto.getDocumentReference());
        r.setDescription(dto.getDescription());
        r.setProject(p);
        return r;
    }
}
