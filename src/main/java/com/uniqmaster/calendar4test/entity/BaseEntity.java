package com.uniqmaster.calendar4test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date createDate;

    @PrePersist
    public void prePersist() {
        createDate = new Date();
    }
}
