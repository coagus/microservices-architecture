package com.coagus.cuenta_movimientos.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.coagus.cuenta_movimientos.enums.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movimiento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime fecha;

  @Column(name = "tipo_movimiento", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoMovimiento tipoMovimiento;

  @Column(nullable = false)
  private Float valor;

  @Column(nullable = false)
  private Float saldo;
  
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "cuenta_id")
  private Cuenta cuenta;
}
