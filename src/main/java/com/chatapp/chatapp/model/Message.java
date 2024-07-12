package com.chatapp.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String username;
    private String messages;
    private String reciverusername;

    public String getReciverusername() {
        return reciverusername;
    }

    public void setReciverusername(String reciverusername) {
        this.reciverusername = reciverusername;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", name=" + name + ", username=" + username + ", messages=" + messages
                + ", reciverusername=" + reciverusername + "]";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

}
