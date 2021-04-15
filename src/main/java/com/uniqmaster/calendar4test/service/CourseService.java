package com.uniqmaster.calendar4test.service;

import com.uniqmaster.calendar4test.dto.CourseMemberDto;
import com.uniqmaster.calendar4test.dto.EventRequestDto;
import com.uniqmaster.calendar4test.entity.Course;
import com.uniqmaster.calendar4test.entity.Event;
import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.entity.Teacher;
import com.uniqmaster.calendar4test.enums.Role;
import com.uniqmaster.calendar4test.repository.CourseRepositrory;
import com.uniqmaster.calendar4test.repository.StudentRepository;
import com.uniqmaster.calendar4test.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepositrory courseRepositrory;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepositrory courseRepositrory, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepositrory = courseRepositrory;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public Course save(Course course) {
        return courseRepositrory.save(course);
    }


    public String addTeacher(CourseMemberDto courseMemberDto) {

        Teacher teacher;

        if (courseMemberDto.getUsername() != null) {
            teacher = teacherRepository.findByUsername(courseMemberDto.getUsername());
        } else if (courseMemberDto.getEmail() != null) {
            teacher = teacherRepository.findByEmail(courseMemberDto.getEmail());
        } else {
            return "user not found";
        }

        try {
            Course course = courseRepositrory.findById(courseMemberDto.getCourseId()).get();
            course.setTeacher(teacher);
            courseRepositrory.save(course);
            return "Teacher successfully added...";
        } catch (Exception e) {
            return "Problem occurred...";
        }

    }

    public Object addStudent(CourseMemberDto courseMemberDto) {
        Student student;

        if (courseMemberDto.getUsername() != null) {
            student = studentRepository.findByUsername(courseMemberDto.getUsername());
        } else if (courseMemberDto.getEmail() != null) {
            student = studentRepository.findByEmail(courseMemberDto.getEmail());
        } else {
            return "user not found";
        }

        try {
            Course course = courseRepositrory.findById(courseMemberDto.getCourseId()).get();
            course.getStudentList().add(student);
            courseRepositrory.save(course);
            return "student successfully added...";
        } catch (Exception e) {
            return "Problem occurred...";
        }
    }

    public Course addEventToCourse(Event event, Long courseId) {
        Course course = courseRepositrory.findById(courseId).get();
        course.getEvents().add(event);
        return courseRepositrory.save(course);
    }

    public List<Course> findAll() {
        return courseRepositrory.findAllByTeacher(getCurrentTeacher());
    }
    public Teacher getCurrentTeacher() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return teacherRepository.findByUsername(((UserDetails) principal).getUsername());

        } else {
            return teacherRepository.findByUsername(principal.toString());
        }
    }

}
