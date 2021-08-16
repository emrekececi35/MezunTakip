package com.example.mezuntakip.repository;


import com.example.mezuntakip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    List<User> findByFirstNameAndAndLastName(String firstName, String lastName);
    List<User> findByGraduteYear(String graduteYear);
}
