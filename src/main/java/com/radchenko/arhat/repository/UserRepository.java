package com.radchenko.arhat.repository;

import com.radchenko.arhat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailContains(String emailPart);
    Optional<User> findByActivationCode(String code);
}
