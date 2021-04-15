package com.uniqmaster.calendar4test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends UserEntity {
    @Column
    private Long Balance;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courseList;

}
