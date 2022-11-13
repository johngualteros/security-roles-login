package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Implementa los getter
@Getter

// Implementa los setter
@Setter

// Implementa un contructor con parametros y uno sin
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
    // Esto sirve pues es el id único que identifica una clase cuando lo serializamos
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    //LAZY = fetch cuando se necesita
    //EAGER = fetch inmediatamente

    // La operación de merge copia el estado del objeto dado en el objeto persistente con el mismo identificador.
    // CascadeType.MERGE propaga la operación de fusión de una entidad principal a una entidad secundaria.
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

}
