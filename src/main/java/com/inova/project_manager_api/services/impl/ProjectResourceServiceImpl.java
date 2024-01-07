package com.inova.project_manager_api.services.impl;


import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.*;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectResourceTableResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.ProjectResource;
import com.inova.project_manager_api.repositories.EmployeeRepo;
import com.inova.project_manager_api.repositories.MstProjectRoleRepo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.repositories.ProjectResourceRepo;
import com.inova.project_manager_api.services.ProjectResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

    @Autowired
    private ProjectResourceDao projectResourceDao;
    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private MstProjectRoleRepo mstProjectRoleRepository;

    @Autowired
    private ProjectResourceRepo projectResourceRepository;

    @Autowired
    private ProjectRepo projectRepository;
    @Override
    public List<ProjectResourceResponseDto> availablePercentage(ResourceRequestDto request) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate from_date = LocalDate.parse(request.getFromDate(), inputFormatter);
        LocalDate to_date = LocalDate.parse(request.getToDate(), inputFormatter);

        String outputFromDateString = from_date.format(outputFormatter);
        String outputToDateString = to_date.format(outputFormatter);

        request.setFromDate(outputFromDateString);
        request.setToDate(outputToDateString);

        List<ProjectResourceResponseDto> availableList = this.projectResourceDao.availablePercentages(request);

        List<Date> arrays = new ArrayList<>();
        for (ProjectResourceResponseDto dto : availableList){
            if(!arrays.contains(dto.getFromDate())){
                arrays.add(dto.getFromDate());
            }
            if(!arrays.contains(dto.getToDate())){
                arrays.add(dto.getToDate());
            }
        }

        Collections.sort(arrays);
        List<DatePair> datePairs = createDatePairs(arrays);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<ProjectResourceResponseDto> finalList = new ArrayList<>();

        for (DatePair pair : datePairs) {

            ProjectResourceResponseDto finalpercentage = new ProjectResourceResponseDto();
            request.setFromDate(dateFormat.format(pair.getFromDate()).toString());
            request.setToDate(dateFormat.format(pair.getToDate()).toString());
            List<ProjectResourceResponseDto> percentagesSum = this.projectResourceDao.availablePercentagesSum(request);
            finalpercentage.setPercentage(percentagesSum.get(0).getPercentage());
            finalpercentage.setFromDate(pair.getFromDate());
            finalpercentage.setToDate(pair.getToDate());
            finalList.add(finalpercentage);
        }
        System.out.println(finalList);
        return finalList;
    }

    @Override
    public List<ResourceAllocationResponseDto> projectAllocation(ResourceRequestDto request) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate from_date = LocalDate.parse(request.getFromDate(), inputFormatter);
        LocalDate to_date = LocalDate.parse(request.getToDate(), inputFormatter);

        String outputFromDateString = from_date.format(outputFormatter);
        String outputToDateString = to_date.format(outputFormatter);

        request.setFromDate(outputFromDateString);
        request.setToDate(outputToDateString);

        List<ResourceAllocationResponseDto> availableList = this.projectResourceDao.availablePercentagesWithProject(request);

        return availableList;
    }

    @Override
    public StandardResponse getProjectsByResource(int employeeId) {
        try {
            List<ProjectResourceTableResponseDto> projectsByResource = projectResourceDao.getProjectsByResource(employeeId);
            return new StandardResponse(
                    200,
                    "Data ",
                    projectsByResource
            );
        } catch (Exception e) {
            e.printStackTrace();
            return  new StandardResponse(
                    400,
                    "error ",
                    null
            );
        }

    }

    @Override
    public ResponseEntity<StandardResponse> sendResourceRequest(SendResourceRequestDto resourceRequest, int projectId, int employeeId) {
        try {
            Optional<Project> optionalProject = projectRepository.findById(projectId);
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

            if (optionalProject.isPresent() || optionalEmployee.isPresent()) {
                // insert to project_resource
                ProjectResource projectResource = new ProjectResource();

                projectResource.setAllocatedDate(resourceRequest.getAllocateDate());
                projectResource.setReleaseDate(resourceRequest.getReleaseDate());
                projectResource.setPercentage(resourceRequest.getPercentage());
                projectResource.setApproved(false);
                projectResource.setEmployee(optionalEmployee.get());
                projectResource.setProject(optionalProject.get());
                projectResource.setPercentage(resourceRequest.getPercentage());
                projectResource.setProjectRole(resourceRequest.getUserRole());

                ProjectResource projectResourceEntity = projectResourceRepository.save(projectResource);

                //ProjectResponseDto resourceResponseDto = projectMapper.toProjectResponseDto(projectResourceEntity.getProject());




                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Project Updated",
                                null
                        ),
                        HttpStatus.OK
                );

            } else {
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "Not Found",
                                null
                        ),
                        HttpStatus.NO_CONTENT
                );
            }
        } catch (Throwable e) {

            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while updating the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    static class DatePair {
        private final Date fromDate;
        private final Date toDate;

        public DatePair(Date fromDate, Date toDate) {
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        public Date getFromDate() {
            return fromDate;
        }

        public Date getToDate() {
            return toDate;
        }
    }

    private static List<DatePair> createDatePairs(List<Date> sortedDates) {
        List<DatePair> datePairs = new ArrayList<>();

        for (int i = 0; i < sortedDates.size() - 1; i++) {
            Date fromDate = sortedDates.get(i);
            Date toDate = sortedDates.get(i + 1);
            datePairs.add(new DatePair(fromDate, toDate));
        }

        return datePairs;
    }
}