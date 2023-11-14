package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.ImpStatusRequestDto;
import com.inova.project_manager_api.dto.response.ImpStatusResponseDto;
import com.inova.project_manager_api.entities.ImpStatus;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ImpStatusMapper {

    public ImpStatusResponseDto toImpResponseDto(ImpStatus i) {
        return new ImpStatusResponseDto(
                i.getId(), i.getStatus(), i.getReason()
        );
    }

    public List<ImpStatusResponseDto> toImpStatusResponseDtoList(List<ImpStatus> list) {
        List<ImpStatusResponseDto> dtoList = new ArrayList<>();
        for (ImpStatus i : list) {
            dtoList.add(toImpResponseDto(i));
        }
        return dtoList;
    }
    public ImpStatus toiImpStatusEntity(ImpStatusRequestDto dto){
        ImpStatus status = new ImpStatus();
        status.setStatus(dto.getStatus());
        status.setReason(dto.getStatus());
        return status;
    }
}
