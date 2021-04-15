package com.uniqmaster.calendar4test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends UserEntity{
    @Column
    private Long salary;
    @OneToMany(mappedBy = "teacher")
    private List<Course> TeacherCourseList;

}
