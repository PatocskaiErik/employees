package com.example.employees.repository;

import com.example.employees.entity.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {

    public List<Programmer> findAllByDeleted(boolean deleted);
}
