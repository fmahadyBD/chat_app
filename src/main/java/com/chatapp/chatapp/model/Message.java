package com.chatapp.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String messages;
    @Override
    public String toString() {
        return "message [id=" + id + ", name=" + name + ", messages=" + messages + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessages() {
        return messages;
    }
    public void setMessages(String messages) {
        this.messages = messages;
    }
    
}
