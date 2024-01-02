package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.SpecificationLevelRequestDto;

import java.util.List;

public interface SpecificationLevelDao {
    List<SpecificationLevelRequestDto> specificationLevel();
}
