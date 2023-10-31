package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.AppResponse;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.URIPrefix;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public StandardResponse findOne(@PathVariable int id){
        StandardResponse standardResponse = new StandardResponse(
                200,
                id+"project details",
                projectService.findProject(id)
        );
        return standardResponse;
    }

    @PostMapping(URIPrefix.ADD_PROJECT_DETAILS)
    public ResponseEntity<AppResponse<ProjectDetailsSubmitResponseDto>> projectDetailsSubmit (@Valid @RequestBody AppRequest<ProjectDetailsSubmitRequestDto> request)throws ApplicationGeneralException {
        try {
            ResponseEntity<AppResponse<ProjectDetailsSubmitResponseDto>> response = this.projectService.projectDetailsSubmit(request);
            return response;
//        } catch (Exception e) {
//            throw new ApplicationGeneralException();
        }catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getStackTrace()[0].getClassName(),
                    e.getStackTrace()[0].getMethodName(), "Error saving project details"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
