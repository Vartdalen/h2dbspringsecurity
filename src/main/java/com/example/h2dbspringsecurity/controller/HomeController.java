package com.example.h2dbspringsecurity.controller;

import com.example.h2dbspringsecurity.model.User;
import com.example.h2dbspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String indexGet(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userRepository);
        return "index";
    }

    private void setUserModel(Model model, Authentication auth, UserRepository userRepository) {
        Optional<User> user = userRepository.findUserByEmail(auth.getName());
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }
    }
}
