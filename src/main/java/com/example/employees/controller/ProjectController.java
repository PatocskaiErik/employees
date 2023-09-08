package com.example.employees.controller;

import com.example.employees.dto.ProjectDTO;
import com.example.employees.entity.Project;
import com.example.employees.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects(Project project) {
        return projectService.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/projects")
    public ProjectDTO addProject(@RequestBody ProjectDTO project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/projects/{id}")
    public ProjectDTO editProject(@PathVariable Long id, @RequestBody ProjectDTO project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
            projectService.deleteProject(id);
    }
}
