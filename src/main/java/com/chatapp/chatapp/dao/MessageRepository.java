package com.chatapp.chatapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatapp.chatapp.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    
} 