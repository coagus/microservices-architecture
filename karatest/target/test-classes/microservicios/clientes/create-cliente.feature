Feature: crear cliente
  
  Background:
    * url 'http://localhost:8080'
    * def random10 = 
    """
    function() {
        return Math.floor(Math.random() * 9000000000) + 1000000000 + '';
    }
    """
    * def random5 = 
    """
    function() {
        return Math.floor(Math.random() * 90000) + 10000 + '';
    }
    """
    * def identificacion = random10()
    * def rdm = random5()
    * def nombre = "Persona Test " + rdm

  Scenario: creación de cliente
    * def cliente_request = 
    """
    {
        "contrasena": "#(rdm)",
        "estado": 1,
        "persona": {
            "nombre": "#(nombre)",
            "genero": "M",
            "edad": 25,
            "identificacion": "#(identificacion)",
            "direccion": "Otavalo sn y principal",
            "telefono": "098254785"
        }
    }
    """
    Given path 'clientes'
    And request cliente_request
    When method post
    Then status 201

    * def expectedNombre = cliente_request.persona.nombre
    * def expectedContrasena = cliente_request.contrasena

    And match response.persona.nombre == expectedNombre
    And match response.contrasena == expectedContrasena

    * def data = 
    """
    {
      "clienteId": #(response.id),
      "nombre": "#(response.persona.nombre)",
      "contrasena": "#(response.contrasena)"
    }
    """
    * print 'data: ', data
