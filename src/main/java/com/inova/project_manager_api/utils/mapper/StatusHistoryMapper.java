package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.entities.ImpStatus;
import com.inova.project_manager_api.entities.StatusHistory;
import com.inova.project_manager_api.entities.Task;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class StatusHistoryMapper {
    public StatusHistoryResponseDto toStatusHistoryResponseDto(StatusHistory sh) {
        return new StatusHistoryResponseDto(
                sh.getId(),sh.getDate(),sh.getDescription()
        );
    }
    public List<StatusHistoryResponseDto> toStatusHistoryList(List<StatusHistory> list) {
        List<StatusHistoryResponseDto> dtoList = new ArrayList<>();
        for (StatusHistory s: list) {
            dtoList.add(toStatusHistoryResponseDto(s));
        }
        return dtoList;
    }
}
