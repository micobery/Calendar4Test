package com.uniqmaster.calendar4test.service;


import com.uniqmaster.calendar4test.dto.EventReportDto;
import com.uniqmaster.calendar4test.dto.EventRequestDto;
import com.uniqmaster.calendar4test.entity.Course;
import com.uniqmaster.calendar4test.entity.Event;
import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.entity.Teacher;
import com.uniqmaster.calendar4test.enums.Role;
import com.uniqmaster.calendar4test.repository.EventRepository;
import com.uniqmaster.calendar4test.repository.StudentRepository;
import com.uniqmaster.calendar4test.repository.TeacherRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CourseService courseService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    //private final EmailNotifyEventPublisher emailNotifyEventPublisher;


    public EventService(EventRepository eventRepository, CourseService courseService, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.eventRepository = eventRepository;
        this.courseService = courseService;
        // this.emailNotifyEventPublisher = emailNotifyEventPublisher;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }
    //add event

    public Event save(EventRequestDto eventRequestDto) {
        Event event = eventRepository.save(eventRequestDto.getEvent());
        Course course = courseService.addEventToCourse(event, eventRequestDto.getCourseId());
        event.setCourse(course);
      /*  EmailNotifyEvent emailNotifyEvent=new EmailNotifyEvent(this,course,event);
        emailNotifyEventPublisher.publishEvent(emailNotifyEvent);*/


        return eventRepository.save(event);
    }


    public List<Event> findEventBetweenDates(EventReportDto eventReportDto) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.STUDENT)) {
            return eventRepository.findAllEventByDateForStudent(getCurrentStudent(), eventReportDto.getStartDate(), eventReportDto.getEndDate());
        } else {
            return eventRepository.findAllEventByDateForTeacher(getCurrentTeacher(), eventReportDto.getStartDate(), eventReportDto.getEndDate());
        }


    }

    public Student getCurrentStudent() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return studentRepository.findByUsername(((UserDetails) principal).getUsername());

        } else {
            return studentRepository.findByUsername(principal.toString());
        }
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
