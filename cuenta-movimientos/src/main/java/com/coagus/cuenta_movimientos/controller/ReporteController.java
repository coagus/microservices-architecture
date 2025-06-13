package com.coagus.cuenta_movimientos.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coagus.cuenta_movimientos.client.ClienteClient;
import com.coagus.cuenta_movimientos.dto.ResponseReporte;
import com.coagus.cuenta_movimientos.entity.Movimiento;
import com.coagus.cuenta_movimientos.repository.MovimientoRepository;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

  @Autowired
  private ClienteClient clienteClient;

  @Autowired
  private MovimientoRepository movimientoRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> getReporte(
      @RequestParam Long clienteId,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {

      // Convertir a LocalDateTime si es necesario
    LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
    LocalDateTime fechaFinDateTime = fechaFin.atTime(23, 59, 59);

    if (fechaInicioDateTime.isAfter(fechaFinDateTime)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fecha de inicio no puede ser mayor a la fecha de fin");
    }

    String nombreCliente = clienteClient.getNombreCliente(clienteId);

    if ("NE".equals(nombreCliente)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no encontrado");
    }

    List<ResponseReporte> transacciones = new ArrayList<>();

    List<Movimiento> movimientos = movimientoRepository.findByCuenta_ClienteIdAndFechaBetween(clienteId, fechaInicioDateTime, fechaFinDateTime);

    for (Movimiento movimiento : movimientos) {
      ResponseReporte responseReporte = new ResponseReporte();
      responseReporte.setFecha(movimiento.getFecha());
      responseReporte.setCliente(nombreCliente);
      
      responseReporte.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
      responseReporte.setTipoCuenta(movimiento.getCuenta().getTipoCuenta().getCodigo());
      responseReporte.setSaldoInicial(movimiento.getCuenta().getSaldoDisponible());
      responseReporte.setEstado(movimiento.getCuenta().getEstado());
      responseReporte.setMovimiento(movimiento.getValor());
      responseReporte.setSaldoDisponible(movimiento.getSaldo());
      
      transacciones.add(responseReporte);
    }

    return ResponseEntity.ok(transacciones);
  }
}
