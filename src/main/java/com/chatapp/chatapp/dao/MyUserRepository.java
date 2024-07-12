package com.chatapp.chatapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatapp.chatapp.model.MyUser;



public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
}
