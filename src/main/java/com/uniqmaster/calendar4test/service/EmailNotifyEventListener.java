package com.uniqmaster.calendar4test.service;

import com.uniqmaster.calendar4test.dto.EmailNotifyEvent;
import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.util.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailNotifyEventListener implements ApplicationListener<EmailNotifyEvent> {
    private final SendEmailService sendEmailService;

    public EmailNotifyEventListener(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Override
    public void onApplicationEvent(EmailNotifyEvent emailNotifyEvent) {
        List<Student> studentList = emailNotifyEvent.getCourse().getStudentList();
        String message = "Hello,notify with name" + emailNotifyEvent.getEvent().getName() + "and date:" +
                emailNotifyEvent.getEvent().getEventDate() + "for course:" +
                emailNotifyEvent.getCourse().getCourseName() + "Added to You calendar";
        for (Student stu : studentList) {
            sendEmailService.sendMail(stu.getEmail(), message);
        }
    }
}
