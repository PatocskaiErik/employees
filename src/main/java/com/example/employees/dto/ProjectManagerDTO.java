package com.example.employees.dto;

import com.example.employees.entity.Programmer;
import com.example.employees.entity.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class ProjectManagerDTO {

    private Long id;

    @Column(name="name")
    @NotBlank(message = "Name can not be empty")
    @Size(min = 3, message = "Username must be minimum 3 letters")
    private String name;

    private String address;

    @Column(name="birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birth;

    @Column(name="phone")
    @Pattern(regexp="(^\\+)?[0-9()-]*", message = "Invalid phone number")
    private String phone;

    @Column(name="email", unique = true)
    @Pattern(regexp="[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+", message = "Invalid e-mail address")
    private String email;

    private Project project;

    private Set<Programmer> juniors;
}
