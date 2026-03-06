package com.example.StudetManagement.repository;

import java.util.List;
import java.util.Optional;

import com.example.StudetManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring Data JPA auto-generates SQL from method names — no queries needed!
    List<Student> findByDepartment(String department);

    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);
}

