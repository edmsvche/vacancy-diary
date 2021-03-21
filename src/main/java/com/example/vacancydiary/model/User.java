package com.example.vacancydiary.model;

import com.example.vacancydiary.model.enums.Roles;
import com.example.vacancydiary.model.enums.Status;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private Roles role;

    @Transient
    private String confirmPassword;
}
