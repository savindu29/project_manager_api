package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.CostResponseDto;
import com.inova.project_manager_api.dto.response.ImpStatusResponseDto;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.entities.Cost;
import com.inova.project_manager_api.entities.ImpStatus;
import com.inova.project_manager_api.entities.StatusHistory;

import java.util.ArrayList;
import java.util.List;

public class ImpStatusMapper {

    public ImpStatusResponseDto toImpResponseDto(ImpStatus i) {
        return new ImpStatusResponseDto(
                i.getId(),i.getStatus(),i.getReason()
        );
    }

    public List<ImpStatusResponseDto> toImpStatusResponseDtoList(List<ImpStatus> list) {
        List<ImpStatusResponseDto> dtoList = new ArrayList<>();
        for (ImpStatus i: list) {
            dtoList.add(toImpResponseDto(i));
        }
        return dtoList;
    }
}
