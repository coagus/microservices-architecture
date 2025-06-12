package com.coagus.cuenta_movimientos.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseReporte {
  private LocalDateTime fecha;
  private String cliente;
  private String numeroCuenta;
  private String tipoCuenta;
  private Float saldoInicial;
  private Boolean estado;
  private Float movimiento;
  private Float saldoDisponible;
}
