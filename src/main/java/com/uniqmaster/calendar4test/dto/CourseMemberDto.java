package com.uniqmaster.calendar4test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMemberDto {
    private String username;
    private String email;
    private Long courseId;
}
