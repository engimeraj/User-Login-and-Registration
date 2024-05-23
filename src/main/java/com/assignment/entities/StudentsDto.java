package com.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentsDto {
    private String name;
    private String email;
    private String password;
    private LocalDate dob;
    private Integer age;
    private Integer birthDay;
    private Integer birthMonth;
    private Integer birthYear;
}
