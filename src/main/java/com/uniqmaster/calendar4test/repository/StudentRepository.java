package com.uniqmaster.calendar4test.repository;

import com.uniqmaster.calendar4test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

    Student findByEmail(String email);
}
