package com.uniqmaster.calendar4test.repository;

import com.uniqmaster.calendar4test.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);

    Teacher findByEmail(String email);
}
