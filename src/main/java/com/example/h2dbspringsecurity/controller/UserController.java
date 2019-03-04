package com.example.h2dbspringsecurity.controller;
import com.example.h2dbspringsecurity.model.User;
import com.example.h2dbspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String user(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userRepository);
        return "user";
    }

    @PostMapping("/user")
    public String user(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            return "redirect:/error";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    private void setUserModel(Model model, Authentication auth, UserRepository userRepository) {
        Optional<User> user = userRepository.findUserByEmail(auth.getName());
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }
    }
}
