package com.riwi.RIWIvirtual.controller;

import com.riwi.RIWIvirtual.dtos.student.StudentDTO;
import com.riwi.RIWIvirtual.entity.Student;

import com.riwi.RIWIvirtual.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student, @RequestParam Long classId) {
        try {
            Student createdStudent = studentService.createStudent(student, classId);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para deshabilitar un estudiante
    @PatchMapping("/{id}/disable")
    public ResponseEntity<?> disableStudent(@PathVariable Long id) {
        try {
            StudentDTO disabledStudent = studentService.disableStudent(id);
            return new ResponseEntity<>(disabledStudent, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getStudents(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Student> students = studentService.getActiveStudents(name, email, page, size);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
