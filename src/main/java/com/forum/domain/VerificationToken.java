package com.forum.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CustomUser user;
}
