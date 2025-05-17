package com.example.bonvoyagehotel.repository;

import com.example.bonvoyagehotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);  // add this
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

