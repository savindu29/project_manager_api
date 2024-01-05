package com.inova.project_manager_api.services.impl;


import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.*;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectResourceTableResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
import com.inova.project_manager_api.services.ProjectResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

    @Autowired
    private ProjectResourceDao projectResourceDao;
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