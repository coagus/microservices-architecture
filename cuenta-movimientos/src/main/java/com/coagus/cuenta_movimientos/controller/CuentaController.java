package com.coagus.cuenta_movimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coagus.cuenta_movimientos.client.ClienteClient;
import com.coagus.cuenta_movimientos.entity.Cuenta;
import com.coagus.cuenta_movimientos.repository.CuentaRepository;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

  @Autowired
  private CuentaRepository cuentaRepository;

  @Autowired
  private ClienteClient clienteClient;

  // Create
  @PostMapping
  public ResponseEntity<?> createCuenta(@RequestBody Cuenta cuenta) {
    if (!clienteClient.existeCliente(cuenta.getClienteId())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no existe");
    }
    
    if (cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta ya existe");
    }
    
    cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
    return ResponseEntity.ok(cuentaRepository.save(cuenta));
  }

  // Read
  @GetMapping
  public List<Cuenta> getAllCuentas() {
    return cuentaRepository.findAll();
  }

  // Update
  @PutMapping("/{id}")
  public Cuenta updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
    Cuenta cuentaExistente = cuentaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

    cuentaExistente.setNumeroCuenta(cuenta.getNumeroCuenta());
    cuentaExistente.setTipoCuenta(cuenta.getTipoCuenta());
    cuentaExistente.setSaldoInicial(cuenta.getSaldoInicial());
    cuentaExistente.setEstado(cuenta.getEstado());
    return cuentaRepository.save(cuentaExistente);
  }  
}
