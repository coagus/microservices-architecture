Feature: crear cuenta y movimientos
  
  Background:
    * url 'http://localhost:8081'
    * def result = callonce read('classpath:microservicios/cuentas/create-cuenta.feature')
    * def data  = result.dataCuenta
    * def random3 = 
    """
    function() {
        return Math.floor(Math.random() * 900) + 100 + '';
    }
    """

  Scenario: crear movimiento
    * def mov_request = 
    """
    {
        "noCuenta": "#(data.cuenta)",
        "monto": #(random3())
    }
    """
    Given path 'movimientos'
    And request mov_request
    When method post
    Then status 200