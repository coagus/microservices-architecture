package com.coagus.cliente_persona.repository;

import com.coagus.cliente_persona.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
}
