package com.chatapp.chatapp.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
}
