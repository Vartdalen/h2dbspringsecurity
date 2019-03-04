package com.example.h2dbspringsecurity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude="ticket")
@ToString(exclude="ticket")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
    private Set<Ticket> ticket;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
