package com.assignment.services;
import com.assignment.entities.Students;
import com.assignment.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Students students = studentsRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: ")
                );

        return new org.springframework.security.core.userdetails.User(
                students.getEmail(), students.getPassword(), Collections.emptyList());
    }
}

