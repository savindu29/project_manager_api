package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;

import java.util.List;

public interface StatusHistoryDao {
    List<StatusHistoryResponseDto> getStatusHistoryOrderByDate(int projectId);
}
