package com.chatapp.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.model.MyUser;
import com.chatapp.chatapp.service.MessageService;
import com.chatapp.chatapp.service.MyUserService;

@Controller
public class ContentController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/home")
    public String handleWelcome(Model model) {
        List<MyUser> allUserWithoutAuth=myUserService.findAll();
        model.addAttribute("alluserwithoutauth",allUserWithoutAuth);
        return "home";
    }

    @GetMapping("/message/{reciverusername}")
   
    public String handleAdminHome(Model model, @PathVariable String reciverusername) {
        // Retrieve all messages for the given user ID
        List<Message> msg = messageService.getAllMessages(reciverusername);
    
        // Correct logging of the messages
        // System.out.println("Retrieved messages: " + msg);
    
        // Add the list of messages to the model with the correct attribute name
        model.addAttribute("allmessage", msg);
    
        // Add a new Message object to the model
        model.addAttribute("abc", new Message());
    
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
    
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } else if (authentication != null) {
            username = authentication.getName();
        }
        System.out.println("Filtered messages: " + msg);

        // Log the username
        // System.out.println("username: " + username);
        // System.out.println("reciver username: " + reciverusername);
    
        // Add the username to the model
        model.addAttribute("usernameofauth", username);
        model.addAttribute("meessageto", reciverusername);
    
        // Return the view name
        return "message";
    }
    

    @PostMapping("/message/{reciverusername}")
    public String savemsg(@ModelAttribute("abc") Message message, Model model ,@PathVariable String reciverusername) {

        List<Message> msg = messageService.getAllMessages(reciverusername);

        model.addAttribute("allmessage", msg);

        model.addAttribute("abc", new Message());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } else if (authentication != null) {
            username = authentication.getName();
        }
      
        System.out.println("Username: "+username);
      
        model.addAttribute("usernameofauth", username);
        model.addAttribute("meessageto", reciverusername);
    

        message.setUsername(username);
        message.setReciverusername(reciverusername);
        messageService.create(message);
        return "redirect:/home";

    }



    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }
}
