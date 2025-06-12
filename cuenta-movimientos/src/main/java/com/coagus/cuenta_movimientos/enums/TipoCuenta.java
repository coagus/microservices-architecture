package com.coagus.cuenta_movimientos.enums;

public enum TipoCuenta {
    CORRIENTE("Corriente"),
    AHORROS("Ahorros");

    private final String codigo;

    TipoCuenta(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoCuenta fromCodigo(String codigo) {
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de tipo de cuenta inválido: " + codigo);
    }
} 