package com.app.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "animals")
public class Animals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

    public Animals(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public Animals() {
    }
}
