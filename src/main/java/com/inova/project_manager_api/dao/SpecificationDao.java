package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.ProjectImplemntationDto;
import com.inova.project_manager_api.dto.request.SpecificationRequestDto;

import java.util.List;

public interface SpecificationDao {
    List<SpecificationRequestDto> specification();
}
