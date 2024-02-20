package com.kode19.authenticationservice.repository;

import com.kode19.authenticationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
