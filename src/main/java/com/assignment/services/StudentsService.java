package com.assignment.services;

import com.assignment.entities.StudentsDto;

import java.time.LocalDate;

public interface StudentsService {
    String saveStudentsDetails(StudentsDto studentsDto);
    public String updateStudentDob(String email, LocalDate dob);
}
