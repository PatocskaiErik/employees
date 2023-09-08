package com.example.employees.dto;

import com.example.employees.entity.Programmer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class ProjectDTO {

    private Long id;

    @Column(name="name")
    @NotBlank(message = "Name can not be empty")
    @Size(min = 3, message = "Username must be minimum 3 letters")
    private String name;

    private Set<Programmer> programmer;

    private String customer;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date start;

    private String description;
}
