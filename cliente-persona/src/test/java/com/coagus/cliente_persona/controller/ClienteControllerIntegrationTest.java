package com.coagus.cliente_persona.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coagus.cliente_persona.entity.Cliente;
import com.coagus.cliente_persona.entity.Persona;
import com.coagus.cliente_persona.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCliente_DeberiaPersistirEnBaseDeDatos() throws Exception {
        Persona persona = new Persona();
        persona.setNombre("INTEGRATION TEST");
        persona.setGenero("M");
        persona.setEdad(30);
        persona.setIdentificacion("123456789");
        persona.setDireccion("Calle Principal 123");
        persona.setTelefono("1234567890");

        Cliente cliente = new Cliente();
        cliente.setContrasena("password123");
        cliente.setEstado(1);
        cliente.setPersona(persona);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());

        Cliente clienteGuardado = clienteRepository.findAll().get(clienteRepository.findAll().size() - 1);
        assertThat(clienteGuardado.getPersona().getNombre()).isEqualTo("INTEGRATION TEST");

        mockMvc.perform(delete("/clientes/{id}", clienteGuardado.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(clienteRepository.findById(clienteGuardado.getId())).isEmpty();
    }
}