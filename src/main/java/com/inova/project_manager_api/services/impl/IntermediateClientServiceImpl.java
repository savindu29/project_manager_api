package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.ExternalContactPersonRequestDto;
import com.inova.project_manager_api.dto.request.IntermediateClientRequestDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.IntermediateClient;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ExternalContactPersonRepo;
import com.inova.project_manager_api.repositories.IntermediateClientRepo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.IntermediateClientService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ExternalContactPersonMapper;
import com.inova.project_manager_api.utils.mapper.IntermediateClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntermediateClientServiceImpl implements IntermediateClientService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ExternalContactPersonRepo externalContactPersonRepo;
    @Autowired
    private IntermediateClientRepo intermediateClientRepo;
    private final IntermediateClientMapper intermediateClientMapper = new IntermediateClientMapper();
    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();


    @Override
    public ResponseEntity<StandardResponse> updateIntermediateClient(IntermediateClientRequestDto dto, int projectId) {
        Optional<Project> project = projectRepo.findById(projectId);

        if(project.isEmpty()){
            return new ResponseEntity<>(
                    new StandardResponse(
                            404,
                            "there is no project with id = " + projectId,
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }else{

            if(project.get().getIntermediateClient() != null){
                int id = project.get().getIntermediateClient().getId();

                IntermediateClient intermediateClient = intermediateClientRepo.findById(id).get();
                intermediateClient.setName(dto.getName());
                intermediateClient.setCountry(dto.getCountry());

                if(intermediateClient.getExternalContactPerson()!=null){
                    ExternalContactPerson ex =null;
                    if(dto.getExternalContactPerson()!=null){
                        ExternalContactPersonRequestDto dtoEx = dto.getExternalContactPerson();
                        ex = externalContactPersonRepo.findById(intermediateClient.getExternalContactPerson().getId()).get();
                        ex.setName(dtoEx.getName() !=null ? dtoEx.getName() : ex.getName());
                        ex.setMobile(dtoEx.getMobile() !=null ? dtoEx.getMobile() : ex.getMobile());
                        ex.setFixTel(dtoEx.getFixTel() !=null ? dtoEx.getFixTel() : ex.getFixTel());
                        ex.setCompanyEmail(dtoEx.getCompanyEmail() !=null ? dtoEx.getCompanyEmail() : ex.getCompanyEmail());
                        ex.setDesignation(dtoEx.getDesignation() !=null ? dtoEx.getDesignation() : ex.getDesignation());
                        ex.setDescription(dtoEx.getDescription() !=null ? dtoEx.getDescription() : ex.getDescription());
                        externalContactPersonRepo.save(ex);
                    }
                    IntermediateClient save = intermediateClientRepo.save(intermediateClient);
                    return new ResponseEntity<>(
                            new StandardResponse(
                                    200,
                                    "Grant Client Updated , project id : " + id,
                                    intermediateClientMapper.toIntermediateClientResponseDto(save)
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
                    intermediateClient.setExternalContactPerson(save);
                    IntermediateClient savedIC = intermediateClientRepo.save(intermediateClient);
                    return new ResponseEntity<>(
                            new StandardResponse(
                                    200,
                                    "Grant client updated with new external contact person",
                                    intermediateClientMapper.toIntermediateClientResponseDto(savedIC)
                            ),
                            HttpStatus.CREATED
                    );
                }



            }else{
                IntermediateClient intermediateClient = intermediateClientMapper.toIntermediateClientEntity(dto);
                ExternalContactPerson externalContactPerson = externalContactPersonMapper.toExternalContactPersonEntity(dto.getExternalContactPerson());
                if(externalContactPerson!=null){
                    ExternalContactPerson newEx = externalContactPersonRepo.save(externalContactPerson);
                    intermediateClient.setExternalContactPerson(newEx);
                }
                IntermediateClient save = intermediateClientRepo.save(intermediateClient);
                project.get().setIntermediateClient(save);
                projectRepo.save(project.get());
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Saved new  Intermediate client",
                                save
                        ),
                        HttpStatus.OK
                );

            }


        }

     //   =========================================================================================================


    }


    @Override
    public ResponseEntity<StandardResponse> getIntermediateClient(int id) {
        Optional<IntermediateClient> byId = intermediateClientRepo.findById(id);
        if (byId.isPresent()) {
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Grant Client",
                            intermediateClientMapper.toIntermediateClientResponseDto(byId.get())
                    ),
                    HttpStatus.OK
            );
        } else {
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
