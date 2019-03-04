package com.example.h2dbspringsecurity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@EqualsAndHashCode(exclude="user")
@ToString(exclude="user")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTicket")
    private long id;
    private Date date;
    private String film;
    private String cinema;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    public Ticket(Date date, String film, String cinema, User user) {
        this.date = date;
        this.film = film;
        this.cinema = cinema;
        this.user = user;
    }
}
