package com.kode19.authenticationservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private long expiryTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}