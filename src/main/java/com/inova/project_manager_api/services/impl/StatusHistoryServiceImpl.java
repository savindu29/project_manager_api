package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.StatusHistoryDao;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.services.StatusHistoryService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {
    @Autowired
    private StatusHistoryDao statusHistoryDao;
    @Override
    public ResponseEntity<StandardResponse> getStatusHistoryOrderByDate(int projectId) {
        try{
            List<StatusHistoryResponseDto> statusHistoryOrderByDate = statusHistoryDao.getStatusHistoryOrderByDate(projectId);
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "data",
                            statusHistoryOrderByDate
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Server error",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
