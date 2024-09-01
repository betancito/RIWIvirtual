package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dtos.StudentDTO;
import com.riwi.RIWIvirtual.entity.RiwiClass;
import com.riwi.RIWIvirtual.entity.Student;
import com.riwi.RIWIvirtual.repository.IRiwiClassRepository;
import com.riwi.RIWIvirtual.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IRiwiClassRepository riwiClassRepository;

    public Student createStudent(Student student, Long classId) {
        // Validar que el email sea único
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Validar que la clase exista
        RiwiClass assignedClass = riwiClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class does not exist"));

        // Asignar la clase al estudiante
        student.setAssignedClass(assignedClass);

        // Guardar el estudiante en la base de datos
        return studentRepository.save(student);

    }

    // Método para deshabilitar un estudiante
    public StudentDTO disableStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        student.setActive(false);
        student = studentRepository.save(student);

        // Convertir Student a StudentDTO
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setActive(student.isActive());
        studentDTO.setCreatedAt(student.getCreatedAt());
        studentDTO.setAssignedClassId(student.getAssignedClass() != null ? student.getAssignedClass().getId() : null);

        return studentDTO;
    }
}
