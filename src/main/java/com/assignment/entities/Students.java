package com.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    @Transient
    private LocalDate dob;
    private Integer age;
    private Integer birthDay;
    private Integer birthMonth;
    private Integer birthYear;

    public void setDob(LocalDate dob) {
        this.dob = dob;
        this.birthDay = dob.getDayOfMonth();
        this.birthMonth = dob.getMonthValue();
        this.birthYear = dob.getYear();
        this.age = calculateAge(dob);
    }

    private Integer calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}

