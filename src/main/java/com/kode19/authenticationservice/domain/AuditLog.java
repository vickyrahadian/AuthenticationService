package com.kode19.authenticationservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}