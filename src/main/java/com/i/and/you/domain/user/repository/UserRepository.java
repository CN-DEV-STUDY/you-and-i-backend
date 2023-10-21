package com.i.and.you.domain.user.repository;

import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByEmail(String email);
}
