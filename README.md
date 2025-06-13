# ARQUITECTURA DE MICROSERVICIOS

## Creación del ambiente

Para levantar el ambiente ejecutar:

```shell
docker compose up -d
```

verificar que estén arriba los servcios de cuenta-movimientos-service y cliente-persona-service

```shell
docker ps
```

si no aparecieran levantarlos

```shell
docker start cuenta-movimientos-service

docker start cliente-persona-service
```

Revisa los servicios en el Service Discovery:  http://localhost:8761

Ejecuta el test con karate ejecutando lo siguiente:

```shell
cd karatest
mvn clean test    
```

Importa al postman el siguiente json para poder realizar las pruebas de los servicios:

resources/Microservicios.postman_collection.json