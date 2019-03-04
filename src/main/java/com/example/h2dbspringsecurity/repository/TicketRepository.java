package com.example.h2dbspringsecurity.repository;

import com.example.h2dbspringsecurity.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}