package com.example.capstoneproject.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username);
}
