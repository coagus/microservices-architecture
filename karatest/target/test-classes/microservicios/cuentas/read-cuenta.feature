Feature: leer cuenta
  
  Background:
    * url 'http://localhost:8081'
    * def result = callonce read('classpath:microservicios/cuentas/create-cuenta.feature')
    * def data  = result.dataCuenta

  Scenario: consulta de cuentas
    Given path 'cuentas'
    When method get
    Then status 200

  Scenario: consulta de cuenta por id
    Given path 'cuentas', data.cuentaId
    When method get
    Then status 200
    And match response.numeroCuenta == data.cuenta
    And match response.saldoInicial == data.saldoInicial
   