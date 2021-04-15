package com.uniqmaster.calendar4test.controller;

import com.uniqmaster.calendar4test.dto.EventReportDto;
import com.uniqmaster.calendar4test.dto.EventRequestDto;
import com.uniqmaster.calendar4test.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //add event with event information and courseId
    //For add Event First must find all teacher course and show it and event add to selected course
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping(value = "/addevent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody EventRequestDto eventRequestDto) throws Exception {
        try {
            return new ResponseEntity(eventService.save(eventRequestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //see all user events with start and end date
    @PreAuthorize("hasAnyAuthority('TEACHER','STUDENT')")
    @PostMapping(value = "/findbetweendate")
    public ResponseEntity findByDay(@RequestBody EventReportDto eventReportDto) throws Exception {
        try {
            return new ResponseEntity(eventService.findEventBetweenDates(eventReportDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
