package com.assignment.repositories;

import com.assignment.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Optional<Students> findByEmail(String usernameOrEmail);
}

