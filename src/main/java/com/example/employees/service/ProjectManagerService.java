package com.example.employees.service;

import com.example.employees.dto.ProjectManagerDTO;
import com.example.employees.entity.ProjectManager;
import com.example.employees.handler.ApiRequestException;
import com.example.employees.repository.ProjectManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;

    private ProjectManagerDTO convertEntityToDto(ProjectManager projectManager) {
        ProjectManagerDTO projectManagerDTO = new ProjectManagerDTO();
        projectManagerDTO.setId(projectManager.getId());
        projectManagerDTO.setName(projectManager.getName());
        projectManagerDTO.setAddress(projectManager.getAddress());
        projectManagerDTO.setBirth(projectManager.getBirth());
        projectManagerDTO.setPhone(projectManager.getPhone());
        projectManagerDTO.setEmail(projectManager.getEmail());
        projectManagerDTO.setProject(projectManager.getProject());
        projectManagerDTO.setJuniors(projectManager.getJuniors());
        return projectManagerDTO;
    }

    public List<ProjectManagerDTO> getAllProjectManagers() {
            return projectManagerRepository.findAll()
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList());
    }

    public ProjectManagerDTO getProjectManagerById(Long id) {
        try {
            ProjectManager projectManager = projectManagerRepository.findById(id).get();
            ProjectManagerDTO projectManagerDTO = new ProjectManagerDTO();
            BeanUtils.copyProperties(projectManager, projectManagerDTO);
            return projectManagerDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("Projectmanager doesn't exist with this id");
        }
    }

    public ProjectManagerDTO saveProjectManager(ProjectManagerDTO projectManagerDTO) {
        try {
            ProjectManager newProjectManager = new ProjectManager();
                newProjectManager.setId(projectManagerDTO.getId());
                newProjectManager.setName(projectManagerDTO.getName());
                newProjectManager.setAddress(projectManagerDTO.getAddress());
                newProjectManager.setBirth(projectManagerDTO.getBirth());
                newProjectManager.setPhone(projectManagerDTO.getPhone());
                newProjectManager.setEmail(projectManagerDTO.getEmail());
                newProjectManager.setProject(projectManagerDTO.getProject());
                newProjectManager.setJuniors(projectManagerDTO.getJuniors());
            projectManagerRepository.save(newProjectManager);
            return projectManagerDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("There is a problem. Please check the filled forms are valid!");
        }
    }

    public ProjectManagerDTO updateProjectManager(Long id, ProjectManagerDTO projectManagerDTO) {
        try {
            ProjectManager updatedProjectManager = new ProjectManager();
            updatedProjectManager.setId(projectManagerDTO.getId());
            updatedProjectManager.setName(projectManagerDTO.getName());
            updatedProjectManager.setAddress(projectManagerDTO.getAddress());
            updatedProjectManager.setBirth(projectManagerDTO.getBirth());
            updatedProjectManager.setPhone(projectManagerDTO.getPhone());
            updatedProjectManager.setEmail(projectManagerDTO.getEmail());
            updatedProjectManager.setProject(projectManagerDTO.getProject());
            updatedProjectManager.setJuniors(projectManagerDTO.getJuniors());

            projectManagerRepository.save(updatedProjectManager.toBuilder().id(id).build());
            return projectManagerDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("There is a problem with edit function. Please check the filled forms are valid!");
        }
    }

    public void deleteProjectManager(Long id) {
        try {
            ProjectManager projectManager = projectManagerRepository.findById(id).get();
            projectManagerRepository.delete(projectManager);
        } catch (Exception exception) {
            throw new ApiRequestException("Projectmanager doesn't exist with this id");
        }
    }

    public List<ProjectManager> findProjectManagersByDeleted(boolean deleted) {
        return projectManagerRepository.findAllByDeleted(deleted);
    }
}
