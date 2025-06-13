Feature: leer cliente
  
  Background:
    * url 'http://localhost:8080'
    * def result = callonce read('classpath:microservicios/clientes/create-cliente.feature')
    * def data  = result.data

  Scenario: consulta de todos los clientes
    Given path 'clientes'
    When method get
    Then status 200

  Scenario: consulta del cliente creado
    Given path 'clientes', data.clienteId
    When method get
    Then status 200

    And match response.persona.nombre == data.nombre
    And match response.contrasena == data.contrasena
  
  Scenario: consulta del nombre del cliente
    Given path 'clientes/' + data.clienteId + '/nombre'
    When method get
    Then status 200
    And match response == data.nombre