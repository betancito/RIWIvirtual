package com.riwi.RIWIvirtual.repositories;

import com.riwi.RIWIvirtual.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
