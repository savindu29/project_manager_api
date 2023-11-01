package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.PriorityResponseDto;
import com.inova.project_manager_api.repositories.PriorityRepo;
import com.inova.project_manager_api.services.PriorityService;
import com.inova.project_manager_api.utils.mapper.PriorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityRepo priorityRepo;
    private final PriorityMapper priorityMapper = new PriorityMapper();

    @Override
    public List<PriorityResponseDto> findAllPriority() {
        return priorityMapper.toPriorityResponseDtoList(
                priorityRepo.findAll()
        );

    }
}
