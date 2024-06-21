package com.mfgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mfgestion.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(User.Role role);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

