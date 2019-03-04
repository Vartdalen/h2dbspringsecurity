package com.example.h2dbspringsecurity.controller;
import com.example.h2dbspringsecurity.model.Ticket;
import com.example.h2dbspringsecurity.model.User;
import com.example.h2dbspringsecurity.repository.TicketRepository;
import com.example.h2dbspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/ticket")
    public String ticket(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userRepository);
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @GetMapping("/tickets")
    public String tickets(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userRepository);
        List<Ticket> ticketList = ticketRepository.findAll();
        model.addAttribute("tickets", ticketList);
        return "tickets";
    }

    @PostMapping("/ticket")
    public String ticket(@ModelAttribute("ticket") Ticket ticket){
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/claim/{ticketId}")
    public String claim(@PathVariable("ticketId") String id) {
        Ticket ticket = ticketRepository.findById(Long.parseLong(id)).get();
        ticket.setUser(getUser(SecurityContextHolder.getContext().getAuthentication(), userRepository).get());
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        Ticket ticket = ticketRepository.findById(Long.parseLong(id)).get();
        ticketRepository.delete(ticket);
        return "redirect:/tickets";
    }

    private void setUserModel(Model model, Authentication auth, UserRepository userRepository) {
        Optional<User> user = getUser(auth, userRepository);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }
    }

    private Optional<User> getUser(Authentication auth, UserRepository userRepository) {
        return userRepository.findUserByEmail(auth.getName());
    }
}
