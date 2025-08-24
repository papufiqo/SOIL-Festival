package com.soil.festival.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String batchId;
    private int recyclabilityScore;

    @Column(columnDefinition = "jsonb")
    private String composition; // store as JSON string

    // getters & setters
}
