package com.coagus.cuenta_movimientos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagus.cuenta_movimientos.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
  List<Movimiento> findByCuenta_ClienteIdAndFechaBetween(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
