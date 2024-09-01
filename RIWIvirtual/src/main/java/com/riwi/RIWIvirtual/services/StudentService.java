package com.riwi.RIWIvirtual.services;

import com.riwi.RIWIvirtual.entity.RiwiClass;
import com.riwi.RIWIvirtual.entity.Student;
import com.riwi.RIWIvirtual.repositories.IRiwiClassRepository;
import com.riwi.RIWIvirtual.repositories.IStudentRepository;
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
}
