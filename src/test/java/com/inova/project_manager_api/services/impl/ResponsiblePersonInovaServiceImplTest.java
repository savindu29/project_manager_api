package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.repositories.ResponsiblePersonInovaRepo;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ResponsiblePersonInovaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResponsiblePersonInovaServiceImplTest {
    /*** unit test cases for findResponsiblePerson ***/

    private ResponsiblePersonInovaMapper responsiblePersonInovaMapper = Mockito.mock(ResponsiblePersonInovaMapper.class);
    private ResponsiblePersonInovaServiceImpl responsiblePersonInovaService = Mockito.mock(ResponsiblePersonInovaServiceImpl.class);
    private StandardResponse standardResponse = Mockito.mock(StandardResponse.class);
    private ResponsiblePersonInovaRepo responsiblePersonInovaRepository = Mockito.mock(ResponsiblePersonInovaRepo.class);
    @Test
    @DisplayName("Testing finding a responsible person by id")
    void shouldReturnsAPersonDetailsWhenProjectIdEqualsOne() {
        int id = 1;
        ResponsiblePersonInova responsiblePersonInova = new ResponsiblePersonInova(1,"Nishantha Perera","0776543210","nishantha.perera@company.lk","nishantha.perera@yahoo.com","AI Engineer","Python");
        ResponsiblePersonInovaResponseDto expectedResponse = new ResponsiblePersonInovaResponseDto();

        // Mock the repository and mapper
        Mockito.when(responsiblePersonInovaRepository.findById(id)).thenReturn(Optional.of(responsiblePersonInova));
        Mockito.when(responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(responsiblePersonInova)).thenReturn(expectedResponse);

        StandardResponse response = new StandardResponse();
        response =  responsiblePersonInovaService.findResponsiblePerson(id);
        assertNotNull(response, "Response should not be null");

        // Check response data
        assertNotNull(response.getData(), "Response data should not be null");
        assertTrue(response.getData() instanceof ResponsiblePersonInovaResponseDto, "Response data should be of type ResponsiblePersonInovaResponseDto");

        // Check content
        ResponsiblePersonInovaResponseDto actualResponse = (ResponsiblePersonInovaResponseDto) response.getData();
        assertEquals(expectedResponse, actualResponse, "Response content should match expected content");

//    // Optionally, verify that the mapper is called with the correct arguments
//    Mockito.verify(responsiblePersonInovaMapper).toResponsiblePersonInovaResponseDto(responsiblePersonInova);
    }
}