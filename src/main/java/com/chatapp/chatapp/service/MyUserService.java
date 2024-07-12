package com.chatapp.chatapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.chatapp.chatapp.dao.MyUserRepository;
import com.chatapp.chatapp.model.MyUser;

@Component
public class MyUserService {

    @Autowired
    private MyUserRepository repo;

    public List<MyUser> findAll() {
        List<MyUser> userWithoutAuth = repo.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        userWithoutAuth = userWithoutAuth.stream()
                .filter(user -> !username.equals(user.getUsername()))
                .collect(Collectors.toList());
        return userWithoutAuth;
    }

}
