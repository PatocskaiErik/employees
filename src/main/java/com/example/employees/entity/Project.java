package com.example.employees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="project")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE project SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name = "project_manager_id")
    @JsonBackReference
    private ProjectManager projectManager;

    @OneToMany
    @JoinColumn(name = "programmer_id")
    private Set<Programmer> programmer;

    @Column(name="customer")
    private String customer;

    @Column(name="start")
    private Date start;

    @Column(name="description")
    private String description;

    private boolean deleted = Boolean.FALSE;
}
