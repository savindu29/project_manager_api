package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.StatusHistoryDao;
import com.inova.project_manager_api.dto.request.StatusHistoryRequestDto;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.StatusHistory;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.repositories.StatusHistoryRepo;
import com.inova.project_manager_api.services.StatusHistoryService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.StatusHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private StatusHistoryDao statusHistoryDao;
    @Autowired
    private StatusHistoryRepo statusHistoryRepo;
    private final StatusHistoryMapper statusHistoryMapper = new StatusHistoryMapper();
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

    @Override
    public ResponseEntity<StandardResponse> updateStatusHistory(List<StatusHistoryRequestDto> dtos, int projectId) {
        try{
            Optional<Project> byId = projectRepo.findById(projectId);

            if (byId.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "there is no project with id = "+projectId,
                                null
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            } else {
                Project project = byId.get();


                if (dtos != null || dtos.size() > 0) {
                    for (StatusHistoryRequestDto request : dtos) {

                        if (request.getId() != -1) {
                            Optional<StatusHistory> status = statusHistoryRepo.findById(request.getId());
                            StatusHistory t = status.get();

                            t.setDate(request.getDate());
                            t.setDescription(request.getDescription());

                            statusHistoryRepo.save(t);
                        } else {
                            StatusHistory statusHistory = statusHistoryMapper.statusHistoryEntity(request);
                            statusHistory.setProject(project);
                            statusHistoryRepo.save(statusHistory);
                        }

                    }
                }
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "updated statusHistory with project id : "+projectId,
                                null
                        ),
                        HttpStatus.OK
                );
            }



        }catch (Exception e){
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
