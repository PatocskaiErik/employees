package com.example.employees.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="managers")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE managers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ProjectManager {

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

    @Column(name="email", unique = true)
    private String email;

    @OneToOne
    private Project project;

    @OneToMany
    private Set<Programmer> juniors;

    private boolean deleted = Boolean.FALSE;
}
