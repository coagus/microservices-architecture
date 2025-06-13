package com.coagus.cliente_persona.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/clientes")
public class ClienteController {    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        log.debug("Creando cliente: {}", cliente);
        return clienteRepository.save(cliente);
    }

    // Read
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAllClientes() {
        log.debug("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteById(@PathVariable Long id) {
        log.debug("Obteniendo cliente por id: {}", id);
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @GetMapping("/{id}/existe")
    @ResponseStatus(HttpStatus.OK)
    public Boolean existeCliente(@PathVariable Long id) {
        log.debug("Verificando si el cliente existe: {}", id);
        return clienteRepository.existsById(id);
    }

    @GetMapping("/{id}/nombre")
    @ResponseStatus(HttpStatus.OK)
    public String getNombreCliente(@PathVariable Long id) {
        log.debug("Obteniendo nombre del cliente: {}", id);
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.isPresent() ? cliente.get().getPersona().getNombre() : "NE";
    }

    // Update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        log.debug("Actualizando cliente con id: {}", id);
        log.debug("Datos del cliente a actualizar: {}", cliente);

        Cliente clienteActual = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        log.debug("Cliente actual: {}", clienteActual);

        clienteActual.setContrasena(cliente.getContrasena());
        clienteActual.setEstado(cliente.getEstado());
        
        Persona personaActual = personaRepository.findById(clienteActual.getPersona().getId()).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaActual.setNombre(cliente.getPersona().getNombre());
        personaActual.setGenero(cliente.getPersona().getGenero());
        personaActual.setEdad(cliente.getPersona().getEdad());
        personaActual.setIdentificacion(cliente.getPersona().getIdentificacion());
        personaActual.setDireccion(cliente.getPersona().getDireccion());
        personaActual.setTelefono(cliente.getPersona().getTelefono());
        
        clienteActual.setPersona(personaActual);
        
        return clienteRepository.save(clienteActual);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String eliminarCliente(@PathVariable Long id) {
        log.debug("Eliminando cliente: {}", id);
        clienteRepository.deleteById(id);
        return "Cliente eliminado correctamente";
    }    
}
