Feature: actualizar cliente
  
  Background:
    * url 'http://localhost:8080'
    * def result = callonce read('classpath:microservicios/clientes/create-cliente.feature')
    * def data  = result.data

  Scenario: actualización del cliente
    * def cliente_request = 
    """
    {
        "contrasena": "4321",
        "estado": 1,
        "persona": {
            "nombre": "Persona Test Actualizado",
            "genero": "M",
            "edad": 25,
            "identificacion": "1913401890101",
            "direccion": "Otavalo sn y principal",
            "telefono": "098254785"
        }
    }
    """
    Given path 'clientes', data.clienteId
    And request cliente_request
    When method put
    Then status 200

    * def expectedNombre = cliente_request.persona.nombre
    * def expectedContrasena = cliente_request.contrasena

    And match response.persona.nombre == expectedNombre
    And match response.contrasena == expectedContrasena