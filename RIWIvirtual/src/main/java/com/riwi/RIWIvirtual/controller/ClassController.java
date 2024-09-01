package com.riwi.RIWIvirtual.controller;

import com.riwi.RIWIvirtual.dto.ClassDTO;
import com.riwi.RIWIvirtual.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(classService.getAllClasses(name, description, page, size));
    }

    // Endpoint para obtener una clase por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(classService.getClassById(id));
    }
}
