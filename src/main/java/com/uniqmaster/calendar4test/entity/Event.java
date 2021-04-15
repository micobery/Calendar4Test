package com.uniqmaster.calendar4test.entity;

import com.uniqmaster.calendar4test.enums.EventType;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Event extends BaseEntity {



    @Column(name = "event_name")
    private String name;


    @Column(name = "event_date")
    private Date eventDate;

    @Column
    private String joinLink;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    private Course course;


}
