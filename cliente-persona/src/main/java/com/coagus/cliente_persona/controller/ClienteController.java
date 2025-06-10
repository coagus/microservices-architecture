package com.coagus.cliente_persona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coagus.cliente_persona.entity.Cliente;
import com.coagus.cliente_persona.entity.Persona;
import com.coagus.cliente_persona.repository.ClienteRepository;
import com.coagus.cliente_persona.repository.PersonaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAllClientes() {
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void crearCliente(@RequestBody Cliente cliente) {
        if (cliente.getPersona().getId() != null) {
            log.info("Cliente ya existe");
            Persona persona = personaRepository.findById(cliente.getPersona().getId()).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            cliente.setPersona(persona);
        } 
        clienteRepository.save(cliente);
    }
}
