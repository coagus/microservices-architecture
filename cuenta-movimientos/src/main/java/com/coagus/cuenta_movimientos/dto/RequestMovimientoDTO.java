package com.coagus.cuenta_movimientos.dto;

import lombok.Data;

@Data
public class RequestMovimientoDTO {
  private String noCuenta;
  private Float monto;
}
