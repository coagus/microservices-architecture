Feature: crear cuenta
  
  Background:
    * url 'http://localhost:8081'
    * def result = callonce read('classpath:microservicios/clientes/create-cliente.feature')
    * def data  = result.data
    * def random10 = 
    """
    function() {
        return Math.floor(Math.random() * 9000000000) + 1000000000 + '';
    }
    """
    * def cuenta = random10()

  Scenario: creación de cuenta
    * def cuenta_equest = 
    """
    {
        "numeroCuenta": "#(cuenta)",
        "tipoCuenta": "CORRIENTE",
        "saldoInicial": 100,
        "saldoDisponible": 100,
        "estado": 1,
        "clienteId": #(data.clienteId)
    }
    """
    Given path 'cuentas'
    And request cuenta_equest
    When method post
    Then status 200

    * def expectedCuenta = cuenta_equest.numeroCuenta
    * def expectedSaldoInicial = cuenta_equest.saldoInicial

    And match response.numeroCuenta == expectedCuenta
    And match response.saldoInicial == expectedSaldoInicial

    * def dataCuenta = 
    """
    {
      "cuentaId": #(response.id),
      "cuenta": "#(response.numeroCuenta)",
      "saldoInicial": #(response.saldoInicial)
    }
    """
    * print 'dataCuenta: ', dataCuenta
   