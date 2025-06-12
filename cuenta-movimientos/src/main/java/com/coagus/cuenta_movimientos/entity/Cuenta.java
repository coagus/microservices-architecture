package com.coagus.cuenta_movimientos.entity;

import java.util.List;

import com.coagus.cuenta_movimientos.enums.TipoCuenta;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cuenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "numero_cuenta", nullable = false, unique = true)
  private String numeroCuenta;

  @Column(name = "tipo_cuenta", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoCuenta tipoCuenta;

  @Column(name = "saldo_inicial", nullable = false)
  private Float saldoInicial;

  @Column(name = "saldo_disponible", nullable = false)
  private Float saldoDisponible;

  @Column(name = "estado", nullable = false)
  private Boolean estado;

  @Column(name = "cliente_id", nullable = false)
  private Long clienteId;

  @JsonManagedReference
  @OneToMany(mappedBy = "cuenta")
  private List<Movimiento> movimientos;
}
