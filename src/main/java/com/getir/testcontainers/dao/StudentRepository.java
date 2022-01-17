package com.getir.testcontainers.dao;

import com.getir.testcontainers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> { }
