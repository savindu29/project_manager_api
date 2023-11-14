package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.utils.StandardResponse;

public interface ResponsiblePersonInovaService {

    StandardResponse updatePerson(int id, ResponsiblePersonInovaRequestDto updatedPerson);
    StandardResponse findAllResponsiblePersons();

    StandardResponse searchAllPersons(int page, int count,String searchtext);

    StandardResponse saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto);

    StandardResponse searchResponsiblePersons();
}
