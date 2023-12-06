package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.DocumentUploadRequestDto;
import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.dto.response.ImageUploadResponseDto;
import com.inova.project_manager_api.entities.OutputsFromInova;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.RfpResource;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.enums.FileType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.repositories.OutputsFromInovaRepo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.repositories.RfpResourceRepo;
import com.inova.project_manager_api.services.DmsService;
import com.inova.project_manager_api.services.DocumentService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private DmsService dmsService;
    @Autowired
    private RfpResourceRepo rfpResourceRepository;
    @Autowired
    private OutputsFromInovaRepo outputsFromInovaRepository;

    @Override
    public ResponseEntity<StandardResponse> uploadImage(List<DocumentUploadRequestDto> request) throws ApplicationGeneralException, IOException {
        String status = null;
        try {
            for (DocumentUploadRequestDto dto:request){
                ResponseEntity<DocumentResponseDto> response =dmsService.uploadFile(dto.getFileString(),dto.getFileType());;


                if (dto.getResource() == DocumentResourceType.RFP) {

                    //update rfp_resources
                    RfpResource rfpResource = new RfpResource();
                    rfpResource.setDescription(dto.getDescription());
                    rfpResource.setDocumentReference(response.getBody().getDocumentReference());

                    Project project = this.projectRepo.findById(dto.getProjectId()).get();
                    rfpResource.setProject(project);

                    RfpResource savedRfpResource = this.rfpResourceRepository.save(rfpResource);
                } else if (dto.getResource() == DocumentResourceType.OFI) {
                    //update outputs_from_inova
                    OutputsFromInova outputsFromInova = new OutputsFromInova();
                    outputsFromInova.setDescription(dto.getDescription());
                    outputsFromInova.setDocumentReference(response.getBody().getDocumentReference());
                    Project project = this.projectRepo.findById(dto.getProjectId()).get();
                    outputsFromInova.setProject(project);

                    OutputsFromInova savedOutputsFromInova = this.outputsFromInovaRepository.save(outputsFromInova);
                }

            }



            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "File UploadedSuccessfully ",
                            null
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "error ",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public ResponseEntity<StandardResponse> getDocuments(int projectId, DocumentResourceType type) {
       try{
           List<RfpResource> byProjectId = rfpResourceRepository.findByProjectId(projectId);
           RfpResource rfp = byProjectId.get(0);
           String documentReference = rfp.getDocumentReference();
           String s = dmsService.downloadFile(documentReference);
           return new ResponseEntity<>(
                   new StandardResponse(
                           200,
                           "base64 string",
                           s
                   ),HttpStatus.OK
           );
       } catch (ApplicationGeneralException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           return new ResponseEntity<>(
                   new StandardResponse(
                           500,
                           "error ",
                           null
                   ),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );

       }
    }
}
