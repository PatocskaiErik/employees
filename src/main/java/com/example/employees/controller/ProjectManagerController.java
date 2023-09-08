package com.example.employees.controller;

import com.example.employees.dto.ProjectManagerDTO;
import com.example.employees.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectManagerController {

    @Autowired
    ProjectManagerService projectManagerService;

    @GetMapping("/projectmanagers")
    public List<ProjectManagerDTO> getProjectManagers() {
        return projectManagerService.getAllProjectManagers();
    }

    @GetMapping("/projectmanagers/{id}")
    public ProjectManagerDTO getProjectManagerById(@PathVariable Long id) {
        return projectManagerService.getProjectManagerById(id);
    }

    @PostMapping("/projectmanagers")
    public ProjectManagerDTO addProjectManager(@RequestBody ProjectManagerDTO projectManager) {
        return projectManagerService.saveProjectManager(projectManager);
    }

    @PutMapping("/projectmanagers/{id}")
    public ProjectManagerDTO editProjectManager(@PathVariable Long id, @RequestBody ProjectManagerDTO projectManager) {
        return projectManagerService.updateProjectManager(id, projectManager);
    }

    @DeleteMapping("/projectmanagers/{id}")
    public void removeProjectManager(@PathVariable Long id) {
            projectManagerService.deleteProjectManager(id);
    }
}
