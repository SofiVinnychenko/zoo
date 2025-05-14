package com.app.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "enclosure")
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private int capacity;

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animals> animals = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enclosure_employees",
            joinColumns = @JoinColumn(name = "enclosure_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_id")
    )
    private Set<Employees> employee = new HashSet<>();

    @OneToMany(mappedBy = "enclose", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedingSchedule> feedingSchedules = new ArrayList<>();

    public Enclosure(String name, String type, int capacity) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    public Enclosure() {
    }

}
