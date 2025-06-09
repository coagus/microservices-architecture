package com.coagus.cliente_persona.repository;

import com.coagus.cliente_persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
