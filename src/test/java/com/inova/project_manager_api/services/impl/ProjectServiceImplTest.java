package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ProjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectServiceImplTest {
/** unit test cases for findProject **/
    private ProjectRepo projectRepository = Mockito.mock(ProjectRepo.class);
    private ProjectMapper projectMapper = Mockito.mock(ProjectMapper.class);
    private ProjectServiceImpl projectService = Mockito.mock(ProjectServiceImpl.class);
//    @Test
//    @DisplayName("Should find project by projectId.")
//    void projectIdEqualsOneReturnsAProject() {
//        int projectId = 1;
//        ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
//        var project = Mockito.when(projectRepository.findById(projectId));
//        //var project = projectServiceImpl.findProject(projectId);
//        assertNotNull(project, "Project with ID " + projectId + " not found");
//    }
    //void shouldFindPostById() {
    //        Post post = new Post(123L, "First Post", "http://url.site", "Test",
    //                0, null, Instant.now(), null);
    //        PostResponse expectedPostResponse = new PostResponse(123L, "First Post", "http://url.site", "Test",
    //                "Test User", "Test Subredit", 0, 0, "1 Hour Ago", false, false);
    //
    //        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));
    //        Mockito.when(postMapper.mapToDto(Mockito.any(Post.class))).thenReturn(expectedPostResponse);
    //
    //        PostResponse actualPostResponse = postService.getPost(123L);
    //
    //        Assertions.assertThat(actualPostResponse.getId()).isEqualTo(expectedPostResponse.getId());
    //        Assertions.assertThat(actualPostResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());
    //    }

//    @Test
//    void noProjectExistsForProjectIdZero() {
//        int projectId = 0;
//        var projectOptional = projectRepo.findById(0);
//        var getProjectDetails = projectMapper.toProjectAdvanceResponseDto(projectOptional.get());
//
//        assertNull(getProjectDetails,"There's no project exists where projectId equals 0");
//        assertEquals(projectId, getProjectDetails.getId(), "ID mismatch");
//    }
    @Test
    @DisplayName("Testing finding a project by projectId.")
    void shouldReturnsAProjectWhenProjectIdEqualsOne() {
        int projectId = 1;
        Project project = new Project();
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        ProjectAdvanceResponseDto expectedResponse = new ProjectAdvanceResponseDto();
        Mockito.when(projectMapper.toProjectAdvanceResponseDto(project)).thenReturn(expectedResponse);

        //StandardResponse actualResponse = projectService.findProject(projectId);
        StandardResponse response = projectService.findProject(projectId);
        ProjectAdvanceResponseDto actualResponse = (ProjectAdvanceResponseDto) response.getData();
        assertEquals(expectedResponse, actualResponse);
    }

}