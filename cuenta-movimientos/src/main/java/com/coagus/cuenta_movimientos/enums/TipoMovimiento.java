package com.coagus.cuenta_movimientos.enums;

public enum TipoMovimiento {
  DEPOSITO("Deposito"),
  RETIRO("Retiro");

  private final String codigo;

  TipoMovimiento(String codigo) {
    this.codigo = codigo;
  }

  public String getCodigo() {
    return codigo;
  }

  public static TipoMovimiento fromCodigo(String codigo) {
    for (TipoMovimiento tipo : TipoMovimiento.values()) {
      if (tipo.getCodigo().equals(codigo)) {
        return tipo;
      }
    }
    throw new IllegalArgumentException("Código de tipo de movimiento inválido: " + codigo);
  }
}
