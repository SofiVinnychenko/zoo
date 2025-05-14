package com.app.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "employees")

public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private int salary;

    @ManyToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Enclosure> enclosures = new HashSet<>();

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<FeedingSchedule> feedingSchedules = new HashSet<>();

    public Employees(String name, String role, int salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public Employees() {
    }
}
