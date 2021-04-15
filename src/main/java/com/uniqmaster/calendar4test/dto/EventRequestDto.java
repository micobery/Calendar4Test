package com.uniqmaster.calendar4test.dto;

import com.uniqmaster.calendar4test.entity.Event;
import com.uniqmaster.calendar4test.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto extends Event {
    private Event event;
    private Long courseId;
}
