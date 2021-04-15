package com.uniqmaster.calendar4test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
public class EventReportDto {
    private Date startDate;
    private Date endDate;
}
