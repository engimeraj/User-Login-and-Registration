package com.assignment.controller;

import com.assignment.config.JwtHelper;
import com.assignment.entities.StudentsDto;
import com.assignment.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentsController {
    @Autowired
    JwtHelper jwtHelper;
    StudentsService studentsService;
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    @PostMapping("/registration")
    public ResponseEntity<String> insertStudentsDetails(@RequestBody StudentsDto studentsDto) {
        String response = studentsService.saveStudentsDetails(studentsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateDob(@RequestBody StudentsDto studentsDto,
                                            @RequestHeader(name = "Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.replace("Bearer ", "");
            String usernameFromToken = jwtHelper.getUsernameFromToken(jwtToken);
            String response = studentsService.updateStudentDob(usernameFromToken, studentsDto.getDob());
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
