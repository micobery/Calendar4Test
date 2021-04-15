package com.uniqmaster.calendar4test.service;

import com.uniqmaster.calendar4test.dto.EmailNotifyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifyEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EmailNotifyEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(EmailNotifyEvent emailNotifyEvent) {
        applicationEventPublisher.publishEvent(emailNotifyEvent);
    }
}
