package com.uniqmaster.calendar4test.controller;

import com.uniqmaster.calendar4test.dto.CourseMemberDto;
import com.uniqmaster.calendar4test.entity.Course;
import com.uniqmaster.calendar4test.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //create a course
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/addcourse", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Course course) throws Exception {
        try {
            return new ResponseEntity(courseService.save(course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //add member (Teacher and Student)to course with email or username
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/addteachertocourse", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTeacher(@RequestBody CourseMemberDto courseMemberDto) throws Exception {
        try {
            return new ResponseEntity(courseService.addTeacher(courseMemberDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @PostMapping(value = "/addstudenttocourse", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addStudent(@RequestBody CourseMemberDto courseMemberDto) throws Exception {
        try {
            return new ResponseEntity(courseService.addStudent(courseMemberDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping(value = "/findallcourse")
    public ResponseEntity findAllTeaherCourse() throws Exception {
        try{
            return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
