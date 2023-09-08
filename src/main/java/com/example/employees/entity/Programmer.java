package com.example.employees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.Date;

@Entity
@Table(name="programmer")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@SQLDelete(sql = "UPDATE programmer SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="birth")
    private Date birth;

    @Column(name="phone")
    private String phone;

    @Email
    @Column(name="email", unique = true)
    private String email;

    @ManyToOne
    @JsonBackReference
    private Project project;

    @ManyToOne
    @JsonIgnore
    private ProjectManager projectManager;

    @Column(name="role")
    private Role role;

    @Column(name="intern")
    private boolean isIntern;

    private boolean deleted = Boolean.FALSE;
}
