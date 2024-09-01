package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dtos.student.StudentClassDTO;
import com.riwi.RIWIvirtual.dtos.student.StudentDTO;
import com.riwi.RIWIvirtual.entity.RiwiClass;
import com.riwi.RIWIvirtual.entity.Student;
import com.riwi.RIWIvirtual.repository.IRiwiClassRepository;
import com.riwi.RIWIvirtual.repository.IStudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


@Service
public class StudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IRiwiClassRepository riwiClassRepository;

    public Student createStudent(Student student, Long classId) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        RiwiClass assignedClass = riwiClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class does not exist"));

        student.setAssignedClass(assignedClass);

        return studentRepository.save(student);
    }

    public StudentDTO disableStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));

        student.setActive(false);  // Deshabilitar el estudiante
        studentRepository.save(student);

        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.isActive(), student.getAssignedClass().getId());
    }

    public StudentClassDTO findById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + studentId));

        return new StudentClassDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.isActive(),
                student.getCreatedAt(),
                student.getAssignedClass()
        );
    }

    public Student updateStudent(Long studentId, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Validar email
        if (updatedStudent.getEmail() == null || updatedStudent.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!updatedStudent.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email should be valid");
        }

        if (studentRepository.findByEmail(updatedStudent.getEmail())
                .filter(student -> !student.getId().equals(studentId))
                .isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Validar clase
        if (updatedStudent.getAssignedClass() != null) {
            RiwiClass assignedClass = riwiClassRepository.findById(updatedStudent.getAssignedClass().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Class does not exist"));
            updatedStudent.setAssignedClass(assignedClass);
        }

        // Actualizar campos
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());
        existingStudent.setActive(updatedStudent.isActive());
        existingStudent.setAssignedClass(updatedStudent.getAssignedClass());

        return studentRepository.save(existingStudent);
    }

    public Page<Student> getActiveStudents(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (name != null && !name.isEmpty()) {
            return studentRepository.findByNameIgnoreCaseAndActiveTrue(name, pageable);
        } else if (email != null && !email.isEmpty()) {
            return studentRepository.findByEmailIgnoreCaseAndActiveTrue(email, pageable);
        } else {
            return studentRepository.findByActiveTrue(pageable);
        }
    }
}
