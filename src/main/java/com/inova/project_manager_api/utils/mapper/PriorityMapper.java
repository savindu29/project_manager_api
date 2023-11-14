package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.PriorityRequestDto;
import com.inova.project_manager_api.dto.response.PriorityResponseDto;
import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.entities.Priority;
import com.inova.project_manager_api.entities.ProjectStatus;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PriorityMapper {
    public PriorityResponseDto toPriorityResponseDto(Priority p) {
        return new PriorityResponseDto(
                p.getId(), p.getName(), p.getCode()
        );
    }

    public List<PriorityResponseDto> toPriorityResponseDtoList(List<Priority> list) {
        List<PriorityResponseDto> dtoList = new ArrayList<>();
        for (Priority i : list) {
            dtoList.add(toPriorityResponseDto(i));
        }
        return dtoList;
    }
    public Priority toPriorityEntity(PriorityRequestDto dto ){
        Priority p = new Priority();
        p.setName(dto.getName());
        p.setCode(dto.getCode());
        return p;
    }

}
