package com.uniqmaster.calendar4test.repository;

import com.uniqmaster.calendar4test.entity.Course;
import com.uniqmaster.calendar4test.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositrory extends JpaRepository<Course,Long> {
    List<Course> findAllByTeacher(Teacher teacher);

}
