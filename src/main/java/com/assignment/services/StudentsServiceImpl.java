package com.assignment.services;

import com.assignment.entities.Students;
import com.assignment.entities.StudentsDto;
import com.assignment.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String saveStudentsDetails(StudentsDto studentsDto) {
        Optional<Students> existingStudentOptional = studentsRepository.findByEmail(studentsDto.getEmail());
        if (existingStudentOptional.isPresent()) {
            return "Email address already exists";
        } else {
            Students student = mapToEntity(studentsDto);
            studentsRepository.save(student);
            return "Registration successful";
        }
    }
    public String updateStudentDob(String email, LocalDate dob) {
        Optional<Students> studentOptional = studentsRepository.findByEmail(email);
        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            student.setDob(dob);
            if (student.getAge() >= 18 && student.getAge() <= 25) {
               studentsRepository.save(student);

            } else {
                return "Students age should be 18 -25";
            }
        } else {
            return "Student not found please register first";
        }
        return "Student Enrolled successfuly";
    }

    private Students mapToEntity(StudentsDto studentsDto) {
        Students student = new Students();
        student.setName(studentsDto.getName());
        student.setEmail(studentsDto.getEmail());
        String encodedPassword = bCryptPasswordEncoder.encode(studentsDto.getPassword());
        student.setPassword(encodedPassword);
        return student;
    }
}
