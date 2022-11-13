package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Implementa los getter
@Setter
// Implementa los setter
@Getter
// Implementa un contrcutor con argumentos y otro sin
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    //Relación de muchos a muchos
    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role(Object o, String role_admin) {
    }
}
