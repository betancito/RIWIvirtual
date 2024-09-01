package com.riwi.RIWIvirtual.repository;

import com.riwi.RIWIvirtual.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Page<Student> findByNameIgnoreCaseAndActiveTrue(String name, Pageable pageable);
    Page<Student> findByEmailIgnoreCaseAndActiveTrue(String email, Pageable pageable);
    Page<Student> findByActiveTrue(Pageable pageable);
}
