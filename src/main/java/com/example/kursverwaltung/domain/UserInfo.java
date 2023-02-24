package com.example.kursverwaltung.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password",length =200, nullable = true)
    private String password;

    @Column(name = "roles",length = 150, nullable = true)
    private String roles;



}
