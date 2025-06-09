package com.coagus.cliente_persona.controller;

import com.coagus.cliente_persona.entity.Persona;
import com.coagus.cliente_persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createPersona(@RequestBody Persona persona) {
        personaRepository.save(persona);
    }
}
