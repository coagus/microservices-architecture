package com.coagus.cuenta_movimientos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-persona")
public interface ClienteClient {
  @GetMapping("clientes/{id}/existe")
  Boolean existeCliente(@PathVariable("id") Long id);

  @GetMapping("clientes/{id}/nombre")
  String getNombreCliente(@PathVariable("id") Long id);
}
