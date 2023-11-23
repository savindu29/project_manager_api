package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.ExternalContactPersonRequestDto;
import com.inova.project_manager_api.dto.request.GrantClientRequestDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ExternalContactPersonRepo;
import com.inova.project_manager_api.repositories.GrantClientRepo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.GrantClientService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.GrantClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GrantClientServiceImpl implements GrantClientService {
    @Autowired
    private  ProjectRepo projectRepo;
    @Autowired
    private ExternalContactPersonRepo externalContactPersonRepo;
    @Autowired
    private GrantClientRepo grantClientRepo;
    private final GrantClientMapper grantClientMapper = new GrantClientMapper();
    @Override
    public ResponseEntity<StandardResponse> updateGrantClient(GrantClientRequestDto dto, int projectId) {
        Optional<Project> byId = projectRepo.findById(projectId);
        if (byId.isEmpty()) {
            return new ResponseEntity<>(
                    new StandardResponse(
                            404,
                            "there is no project with id = "+projectId,
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        } else {
            int id = byId.get().getGrantClient().getId();
            GrantClient grantClient = grantClientRepo.findById(id).get();
            grantClient.setName(dto.getName() != null ? dto.getName() : grantClient.getName());
            grantClient.setCountry(dto.getCountry() != null ? dto.getCountry() : grantClient.getCountry());
            grantClient.setIsForeign(dto.getIsForeign());

            if(grantClient.getExternalContactPerson()!=null){
                ExternalContactPerson ex =null;
                if(dto.getExternalContactPerson()!=null) {
                    ExternalContactPersonRequestDto dtoEx = dto.getExternalContactPerson();
                    ex = externalContactPersonRepo.findById(grantClient.getExternalContactPerson().getId()).get();
                    ex.setName(dtoEx.getName() !=null ? dtoEx.getName() : ex.getName());
                    ex.setMobile(dtoEx.getMobile() !=null ? dtoEx.getMobile() : ex.getMobile());
                    ex.setFixTel(dtoEx.getFixTel() !=null ? dtoEx.getFixTel() : ex.getFixTel());
                    ex.setCompanyEmail(dtoEx.getCompanyEmail() !=null ? dtoEx.getCompanyEmail() : ex.getCompanyEmail());
                    ex.setDesignation(dtoEx.getDesignation() !=null ? dtoEx.getDesignation() : ex.getDesignation());
                    ex.setDescription(dtoEx.getDescription() !=null ? dtoEx.getDescription() : ex.getDescription());
                    externalContactPersonRepo.save(ex);
                }
                GrantClient save = grantClientRepo.save(grantClient);
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Grant Client Updated , project id : " + id,
                                grantClientMapper.toGrantClientResponseDto(save)
                        ),
                        HttpStatus.CREATED
                );


            }else{
                ExternalContactPerson ex =null;
                ExternalContactPerson save = null;

                if(dto.getExternalContactPerson()!=null) {
                    ExternalContactPersonRequestDto dtoEx = dto.getExternalContactPerson();
                    ex = new ExternalContactPerson();
                    ex.setName(dtoEx.getName() !=null ? dtoEx.getName() : ex.getName());
                    ex.setMobile(dtoEx.getMobile() !=null ? dtoEx.getMobile() : ex.getMobile());
                    ex.setFixTel(dtoEx.getFixTel() !=null ? dtoEx.getFixTel() : ex.getFixTel());
                    ex.setCompanyEmail(dtoEx.getCompanyEmail() !=null ? dtoEx.getCompanyEmail() : ex.getCompanyEmail());
                    ex.setDesignation(dtoEx.getDesignation() !=null ? dtoEx.getDesignation() : ex.getDesignation());
                    ex.setDescription(dtoEx.getDescription() !=null ? dtoEx.getDescription() : ex.getDescription());
                    save= externalContactPersonRepo.save(ex);

                }
                grantClient.setExternalContactPerson(save);
                GrantClient savedGC = grantClientRepo.save(grantClient);
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Grant client updated with new external contact person",
                                grantClientMapper.toGrantClientResponseDto(savedGC)
                        ),
                        HttpStatus.CREATED
                );
            }
        }
    }

    @Override
    public ResponseEntity<StandardResponse> getGrantClient(int id) {

            Optional<GrantClient> byId = grantClientRepo.findById(id);
            if(byId.isPresent()){
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Grant Client",
                                grantClientMapper.toGrantClientResponseDto(byId.get())
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "Grant client not found",
                                null
                        ),
                        HttpStatus.NOT_FOUND
                );
            }

    }
}
