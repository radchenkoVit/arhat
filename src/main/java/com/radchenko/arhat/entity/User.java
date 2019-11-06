package com.radchenko.arhat.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100,  nullable = false)
    private String password;
    @Column(length = 100, name = "activation_code")
    private String activationCode;
    @Column(columnDefinition = "false")
    private boolean active;
    @Enumerated(EnumType.STRING)
    @Column(length = 100,  nullable = false, name = "role")
    private Role role;
}
