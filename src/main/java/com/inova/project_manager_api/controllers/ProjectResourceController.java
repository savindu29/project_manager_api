package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.request.SendResourceRequestDto;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.ProjectResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.URIPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectResourceController {

    @Autowired
    private ProjectResourceService projectResourceService;

    @PostMapping("/available_percentages")
    public ResponseEntity<List<ProjectResourceResponseDto>> projectResourcePercentage(@RequestBody ResourceRequestDto request) {

        return new ResponseEntity<>(

                projectResourceService.availablePercentage(request),
                HttpStatus.OK
        );

    }

    @PostMapping("/project_allocation")
    public ResponseEntity<List<ResourceAllocationResponseDto>> projectAllocation(@RequestBody ResourceRequestDto request) {

        return new ResponseEntity<>(

                projectResourceService.projectAllocation(request),
                HttpStatus.OK
        );

    }
    @GetMapping("/resources-projects/{employeeId}")
    public ResponseEntity<StandardResponse> getProjectsByResource(@PathVariable int employeeId) {

        return new ResponseEntity<>(
                projectResourceService.getProjectsByResource(employeeId),
                HttpStatus.OK
        );

    }

    @PostMapping(URIPrefix.REQUEST + URIPrefix.RESOURCE + URIPrefix.SEND_REQUEST)
    public ResponseEntity<StandardResponse> sendResourceRequest(@RequestBody SendResourceRequestDto resourceRequest, @RequestParam int projectId, @RequestParam int employeeId) throws ApplicationGeneralException {

        try {
            ResponseEntity<StandardResponse> resourceRequestDetails = projectResourceService.sendResourceRequest(resourceRequest, projectId, employeeId);
            return resourceRequestDetails;
        } catch (Exception e) {
            throw new ApplicationGeneralException(e.getMessage());
        }

    }

}
