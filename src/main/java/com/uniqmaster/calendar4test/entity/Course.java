package com.uniqmaster.calendar4test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity {

    @Column(name = "course_name",nullable = false)
    private String courseName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date endDate;

    @ManyToOne()
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> studentList;

    @OneToMany
    private List<Event> events;

}
