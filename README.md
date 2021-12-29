# CHALLENGE BACKEND - Java Spring Boot(API)

## Objetivo

Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los
personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá
exponer la información para que cualquier frontend pueda consumirla.

## Tecnologías

La API se desarrollo utilizando ``` Spring Boot``` siguiendo el patron REST

## Autenticación

Para la autenticacion del usuario, se utilizo ``` JWT ``` para generar los tokens. Estos son necesario para utilizar los endpoints de la API.


## Base de datos

Para la creacion y persistencian del modelo, se decidio utilizar la base de datos ``` H2 ``` por su facil uso e implementacion.
Para interactuar con la base de datos una vez levantada la API, ingresar a ``` localhost:8080/h2 ```.

## Documentacion de la API

Para documentar la api, se utilizo Postman:

[Documentacion en Postman](https://www.postman.com/pablo97758/workspace/challenge-alkemy/collection/11694978-f5d65b3a-1640-4fa0-ad4d-4ca53c932ea3?ctx=documentation)
Esta es una coleccion que contiene todos los request a los endpoints disponibles. Para probarlos, se debera realizar un Fork y utilizar la aplicacion de escritorio de Postman.   

***

**nota:** _Para que funcione el servicio de envio de email de bienvenida que se pide en el item 11, se debe descomentar el codigo del archivo application.properties en la carpeta /resources_