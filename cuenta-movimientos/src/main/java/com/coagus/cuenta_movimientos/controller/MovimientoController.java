package com.coagus.cuenta_movimientos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import com.coagus.cuenta_movimientos.dto.RequestMovimientoDTO;
import com.coagus.cuenta_movimientos.entity.Cuenta;
import com.coagus.cuenta_movimientos.entity.Movimiento;
import com.coagus.cuenta_movimientos.enums.TipoMovimiento;
import com.coagus.cuenta_movimientos.repository.CuentaRepository;
import com.coagus.cuenta_movimientos.repository.MovimientoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

  @Autowired
  private MovimientoRepository movimientoRepository;

  @Autowired
  private CuentaRepository cuentaRepository;

  // Create
  @PostMapping
  public ResponseEntity<?> createMovimiento(@RequestBody RequestMovimientoDTO movimiento) {
    log.info("Creando movimiento: {}", movimiento);

    Optional<Cuenta> cuentaOpt = cuentaRepository.findByNumeroCuenta(movimiento.getNoCuenta());
    if (cuentaOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe cuenta");
    }

    Cuenta cuenta = cuentaOpt.get();   

    if (movimiento.getMonto() < 0 && movimiento.getMonto()*-1 > cuenta.getSaldoDisponible()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente");
    }   
    
    cuenta.setSaldoDisponible(cuenta.getSaldoDisponible() + movimiento.getMonto());

    Movimiento movimientoEntity = new Movimiento();
    movimientoEntity.setCuenta(cuenta);
    movimientoEntity.setFecha(LocalDateTime.now());
    movimientoEntity.setTipoMovimiento(movimiento.getMonto() > 0 ? TipoMovimiento.DEPOSITO : TipoMovimiento.RETIRO);
    movimientoEntity.setValor(movimiento.getMonto());
    movimientoEntity.setSaldo(cuenta.getSaldoDisponible());

    cuentaRepository.save(cuenta);    

    return ResponseEntity.ok(movimientoRepository.save(movimientoEntity));
  }

  // Read
  @GetMapping
  public List<Movimiento> getAllMovimientos() {
    return movimientoRepository.findAll();
  }

  // Update
  @PutMapping("/{id}")
  public Movimiento updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
    Movimiento movimientoExistente = movimientoRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

    movimientoExistente.setFecha(LocalDateTime.now());
    movimientoExistente.setTipoMovimiento(movimiento.getTipoMovimiento());
    movimientoExistente.setValor(movimiento.getValor());
    movimientoExistente.setSaldo(movimiento.getSaldo());
    return movimientoRepository.save(movimientoExistente);
  }
}
