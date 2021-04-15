package com.uniqmaster.calendar4test.dto;

import com.uniqmaster.calendar4test.entity.Course;
import com.uniqmaster.calendar4test.entity.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class EmailNotifyEvent extends ApplicationEvent {
    private Course course;
    private Event event;

    public EmailNotifyEvent(Object source, Course course, Event event) {
        super(source);
        this.event = event;
        this.course = course;
    }
}
