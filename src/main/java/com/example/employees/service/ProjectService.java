package com.example.employees.service;

import com.example.employees.dto.ProjectDTO;
import com.example.employees.entity.Project;
import com.example.employees.handler.ApiRequestException;
import com.example.employees.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private ProjectDTO convertEntityToDto(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setProgrammer(project.getProgrammer());
        projectDTO.setCustomer(project.getCustomer());
        projectDTO.setStart(project.getStart());
        projectDTO.setDescription(project.getDescription());
        return projectDTO;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        try {
            Project project = projectRepository.findById(id).get();
            ProjectDTO projectDTO = new ProjectDTO();
            BeanUtils.copyProperties(project, projectDTO);
            return projectDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("Project doesn't exist with this id");
        }
    }

    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        try {
            Project newProject = new Project();
            newProject.setId(projectDTO.getId());
            newProject.setName(projectDTO.getName());
            newProject.setProgrammer(projectDTO.getProgrammer());
            newProject.setCustomer(projectDTO.getCustomer());
            newProject.setStart(projectDTO.getStart());
            newProject.setDescription(projectDTO.getDescription());
            projectRepository.save(newProject);
            return projectDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("There is a problem. Please check the filled forms are valid!");
        }
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        try {
            Project updatedProject = new Project();
            updatedProject.setId(projectDTO.getId());
            updatedProject.setName(projectDTO.getName());
            updatedProject.setProgrammer(projectDTO.getProgrammer());
            updatedProject.setCustomer(projectDTO.getCustomer());
            updatedProject.setStart(projectDTO.getStart());
            updatedProject.setDescription(projectDTO.getDescription());
            projectRepository.save(updatedProject.toBuilder().id(id).build());
            return projectDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("There is a problem with edit function. Please check the filled forms are valid!");
        }
    }

    public void deleteProject(Long id) {
        try {
            Project project = projectRepository.findById(id).get();
            projectRepository.delete(project);
        } catch (Exception exception) {
            throw new ApiRequestException("Project doesn't exist with this id");
        }
    }

    public List<Project> findProjectsByDeleted(boolean deleted) {
        return projectRepository.findAllByDeleted(deleted);
    }
}
