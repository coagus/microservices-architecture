Feature: eliminar cliente
  
  Background:
    * url 'http://localhost:8080'
    * def result = callonce read('classpath:microservicios/clientes/create-cliente.feature')
    * def data  = result.data

  Scenario: eliminación del cliente
    Given path 'clientes', data.clienteId
    When method delete
    Then status 200