package com.example.employees.repository;

import com.example.employees.entity.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long> {

    public List<ProjectManager> findAllByDeleted(boolean deleted);
}
