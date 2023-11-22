package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.StatusHistoryRequestDto;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.entities.StatusHistory;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class StatusHistoryMapper {
    public StatusHistoryResponseDto toStatusHistoryResponseDto(StatusHistory sh) {
        return new StatusHistoryResponseDto(
                sh.getId(), sh.getDate(), sh.getDescription()
        );
    }

    public List<StatusHistoryResponseDto> toStatusHistoryList(List<StatusHistory> list) {
        List<StatusHistoryResponseDto> dtoList = new ArrayList<>();
        for (StatusHistory s : list) {
            dtoList.add(toStatusHistoryResponseDto(s));
        }
        return dtoList;
    }
    public  StatusHistory statusHistoryEntity(StatusHistoryRequestDto dto ){
        StatusHistory sh = new StatusHistory();
        sh.setDate(dto.getDate());
        sh.setDescription(dto.getDescription());
        return sh;
    }
//    public List<StatusHistory> statusHistoryEntityList (List<StatusHistoryRequestDto> dto){
//
//    }
}
