# CHALLENGE BACKEND - Java Spring Boot(API)

*nota:* _Para que el codigo compile, se debe descomentar el codigo en el archivo application.properties en la carpeta resources_

## Objetivo

Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los
personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá
exponer la información para que cualquier frontend pueda consumirla.

## Tecnologías

La API se desarrollo utilizando ``` Spring Boot``` siguiendo el patrong REST

## Autenticación

Para la autenticacion del usuario, se utilizo ``` JWT ``` para generar los tokens. Estos son necesario para utilizar los endpoints de la API.


## Base de datos

Para la creacion y almacenamiento del modelo, se decidio utilizar la base de datos ``` H2 ``` por su facil uso e implementacion.
Para interactuar con la base de datos una vez levantada la API, ingresar a ``` local:8080/h2 ```.

## Documentacion de la API

Para documentar la api, se utilizo Postman:
[Documentacion en Postman](https://www.postman.com/pablo97758/workspace/challenge-alkemy/collection/11694978-f5d65b3a-1640-4fa0-ad4d-4ca53c932ea3?ctx=documentation)


