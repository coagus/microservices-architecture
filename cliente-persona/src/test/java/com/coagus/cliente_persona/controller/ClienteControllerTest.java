package com.coagus.cliente_persona.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coagus.cliente_persona.entity.Cliente;
import com.coagus.cliente_persona.entity.Persona;
import com.coagus.cliente_persona.repository.ClienteRepository;
import com.coagus.cliente_persona.repository.PersonaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private PersonaRepository personaRepository;

    private Cliente cliente;
    private Persona persona;

    @Test
    void crearCliente_DeberiaRetornarClienteCreado() throws Exception {
        persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan Pérez");
        persona.setGenero("M");
        persona.setEdad(30);
        persona.setIdentificacion("123456789");
        persona.setDireccion("Calle Principal 123");
        persona.setTelefono("1234567890");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setContrasena("password123");
        cliente.setEstado(1);
        cliente.setPersona(persona);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);


        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.persona.nombre").value("Juan Pérez"));
    }
}