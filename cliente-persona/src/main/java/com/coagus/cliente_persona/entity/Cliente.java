package com.coagus.cliente_persona.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contrasena;
    private Integer estado;
    
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id", unique = true)
    private Persona persona;
}
