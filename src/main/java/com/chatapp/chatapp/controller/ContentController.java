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
import org.springframework.web.bind.annotation.PostMapping;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.service.MessageService;
@Controller
public class ContentController {

 
  @Autowired
  private MessageService messageService;


  @GetMapping("/home")
  public String handleWelcome() {
    return "home";
  }

  @GetMapping("/admin/home")
public String handleAdminHome(Model model) {
    List<Message> msg = messageService.getAllMessages(); 
    // Correct logging of the messages

 
    model.addAttribute("allmessage", msg);  // Ensure attribute name is correct and matches the template

    model.addAttribute("abc", new Message());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;

    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        username = userDetails.getUsername();
    } else if (authentication != null) {
        username = authentication.getName();
    }
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println(username);

    // String res="";
    // for (Message message : msg) {
    //   if(message.getName()!=username){
    //     res=message.getName();
    //     break;
    //   }
    //     // System.out.println("Message: " + message.getName());
    // }

    // model.addAttribute("", res);
    model.addAttribute("usernameofauth", username);


    return "home_admin";
}



  @PostMapping("/admin/home")
  public String savemsg(@ModelAttribute("abc") Message message, Model model){

    

        List<Message> msg = messageService.getAllMessages(); 
      
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
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(username);
        // System.out.println(username);
        model.addAttribute("usernameofauth", username);




        message.setName(username);
        messageService.create(message);
        // return "home_admin";
        return "redirect:/admin/home";

    
  }

  @GetMapping("/user/home")
  public String handleUserHome() {
    return "home_user";
  }

  @GetMapping("/login")
  public String handleLogin() {
    return "custom_login";
  }
}
