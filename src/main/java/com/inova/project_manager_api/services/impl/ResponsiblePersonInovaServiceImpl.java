package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dao.ResponsiblePersonInovaDao;
import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.paginatedData.PaginatedResponsiblePersonData;
import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.exceptions.PersonNotFoundException;
import com.inova.project_manager_api.repositories.ResponsiblePersonInovaRepo;
import com.inova.project_manager_api.services.ResponsiblePersonInovaService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ResponsiblePersonInovaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsiblePersonInovaServiceImpl implements ResponsiblePersonInovaService {
    @Autowired
    private ResponsiblePersonInovaRepo responsiblePersonInovaRepo;

    @Autowired
    private ResponsiblePersonInovaDao responsiblePersonInovaDao;
    private final ResponsiblePersonInovaMapper responsiblePersonInovaMapper = new ResponsiblePersonInovaMapper();


    @Override
    public StandardResponse updatePerson(int id, ResponsiblePersonInovaRequestDto updatedPerson) {
        Optional<ResponsiblePersonInova> existingPersonOptional =   responsiblePersonInovaRepo.findById(id);

        if (existingPersonOptional.isPresent()) {
            // Existing person found
            ResponsiblePersonInova existingPerson = existingPersonOptional.get();

            // Update the person's details with the provided data
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setCompanyEmail(updatedPerson.getCompanyEmail());
            existingPerson.setMobile(updatedPerson.getMobile());
            existingPerson.setPrivateEmail(updatedPerson.getPrivateEmail());
            existingPerson.setDesignation(updatedPerson.getDesignation());
            existingPerson.setSpecializedField(updatedPerson.getSpecializedField());

            // Save the updated person to the database
            ResponsiblePersonInova save = responsiblePersonInovaRepo.save(existingPerson);
            if (save!=null){
                return new StandardResponse(
                        200,
                        "Data list",
                        responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(save)
                );

            }else{
                return new StandardResponse(
                        400,
                        "error",
                        null
                );
            }
        } else {

            // Person not found, you can handle this case as needed
            throw new PersonNotFoundException("Person not found with ID: " + id);
        }
    }

    @Override
    public StandardResponse findAllResponsiblePersons() {

        List<ResponsiblePersonInovaResponseDto> responsiblePersonInovaResponseDtos = responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDtoList(
                responsiblePersonInovaRepo.findAll()
        );
        if(responsiblePersonInovaResponseDtos.size()>0){
            return new StandardResponse(
                    200,
                    "Data list",
                    responsiblePersonInovaResponseDtos
            );
        }else{

            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        }



    }

    @Override
    public StandardResponse searchAllPersons(int page, int count,String searchtext) {
        List<ResponsiblePersonInovaResponseDto> searchAllPersons = responsiblePersonInovaDao.searchAllProjects(page, count,searchtext);
        PaginatedResponsiblePersonData paginatedResponsiblePersonData = new PaginatedResponsiblePersonData(
                responsiblePersonInovaDao.getProjectCount(),
                searchAllPersons
        );
        if (paginatedResponsiblePersonData.getCount() == 0) {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        } else {
            return new StandardResponse(
                    200,
                    "Data list",
                    paginatedResponsiblePersonData
            );
        }
    }

    @Override
    public StandardResponse saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto) {
        ResponsiblePersonInova savedData = responsiblePersonInovaRepo.save(
                responsiblePersonInovaMapper.toResponsiblePersonInovaEntity(dto)
        );
        ResponsiblePersonInovaResponseDto responsiblePersonInovaResponseDto = responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(savedData);
        if(responsiblePersonInovaResponseDto==null){
            return new StandardResponse(
                    500,
                    "Server error",
                    null
            );
        }else{
            return new StandardResponse(
                    200,
                    "Saved Data",
                    responsiblePersonInovaResponseDto
            );
        }
    }

    @Override
    public StandardResponse searchEmployeeByName(String searchtext) {
        List<ResponsiblePersonInovaResponseDto> searchAllPersons = responsiblePersonInovaDao.searchEmployeeByname(searchtext);
        PaginatedResponsiblePersonData paginatedResponsiblePersonData = new PaginatedResponsiblePersonData(
                responsiblePersonInovaDao.getProjectCount(),
                searchAllPersons
        );
        if (paginatedResponsiblePersonData.getCount() == 0) {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        } else {
            return new StandardResponse(
                    200,
                    "Data list",
                    paginatedResponsiblePersonData
            );
        }
    }

    @Override
    public StandardResponse findProject(int intId) {
        Optional<ResponsiblePersonInova> byId = responsiblePersonInovaRepo.findById(intId);
        ResponsiblePersonInovaResponseDto responsiblePersonInovaResponseDto = responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(byId.get());
        if (responsiblePersonInovaResponseDto == null) {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        } else {
            return new StandardResponse(
                    200,
                    "Data ",
                    responsiblePersonInovaResponseDto
            );
        }
    }


}
