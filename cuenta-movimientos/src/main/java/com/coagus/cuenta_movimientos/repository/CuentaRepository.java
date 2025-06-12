package com.coagus.cuenta_movimientos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagus.cuenta_movimientos.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

  Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
