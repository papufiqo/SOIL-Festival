package com.soil.festival.controller;

import com.soil.festival.model.Material;
import com.soil.festival.repository.MaterialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {
    private final MaterialRepository repo;

    public MaterialController(MaterialRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Material> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Material create(@RequestBody Material material) {
        return repo.save(material);
    }
}
