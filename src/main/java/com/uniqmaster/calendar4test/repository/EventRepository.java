package com.uniqmaster.calendar4test.repository;


import com.uniqmaster.calendar4test.entity.Event;
import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.entity.Teacher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select u from Event u inner join u.course c inner join c.teacher t where t=?1" +
            " and u.eventDate between ?2 and ?3")
    List<Event> findAllEventByDateForTeacher(Teacher user, Date startDate, Date endDate);


    @Query("select u from Event u inner join u.course c where ?1 member of c.studentList" +
            " and u.eventDate between ?2 and ?3")
    List<Event> findAllEventByDateForStudent(Student user, Date startDate, Date endDate);


}
