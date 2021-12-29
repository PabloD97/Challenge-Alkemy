# CHALLENGE BACKEND - Java Spring Boot(API)

## Indice
- [Objetivo](#id-section1)
- [Tecnología](#id-section2)
- [Autenticacion](#id-section3)
- [Base de datos](#id-section4)
- [Generos](#id-section5)
- [Documentacion de la API](#id-section6)
- [Email de bienvenida](#id-section7)


<div id='id-section1'/>

## Objetivo

Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los
personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá
exponer la información para que cualquier frontend pueda consumirla.


<div id='id-section2'/>

## Tecnología

La API se desarrollo utilizando ``` Spring Boot``` siguiendo el patron REST


<div id='id-section3'/>

## Autenticación

Para la autenticacion del usuario, se utilizo ``` JWT ``` para generar los tokens. Estos son necesario para utilizar los endpoints de la API.


<div id='id-section4'/>

## Base de datos

Para la creacion y persistencian del modelo, se decidio utilizar la base de datos ``` H2 ``` por su facil uso e implementacion.
Para interactuar con la base de datos una vez levantada la API, ingresar a ``` localhost:8080/h2 ```.


<div id='id-section5'/>

## Generos

Los datos del modelo ```Gender``` se cargan cuando se levanta la API.

| ID  |	NAME |
|-----|------|
|1	  | Comedy |
|2	  | Crime	|
|3	  | Thriller	|
|4	  | Adventure|
|5	  | Sport	|
|6	  | Family	|
|7	  | Fantasy|
|8	  | Action	|
|9	  | Detective|
|10	| Horror	|
|11	| Romance	|
|12	| Biography|	
|13	| History	|
|14	| Mystery	|
|15	| Musical|	
|16	| Sci-fi|	
|17	| War	|
|18	| Animation|	
|19	| Western|	
|20	| Drama	|

De esta manera, se evita que se carguen generos erroneos o repetidos.


<div id='id-section6'/>

## Documentacion de la API

Para documentar la api, se utilizo Postman:

[Documentacion en Postman](https://www.postman.com/pablo97758/workspace/challenge-alkemy/collection/11694978-f5d65b3a-1640-4fa0-ad4d-4ca53c932ea3?ctx=documentation)
Esta es una coleccion que contiene todos los request a los endpoints disponibles. Para probarlos, se debera realizar un Fork y utilizar la aplicacion de escritorio de Postman.   


<div id='id-section7'/>

## Email de bienvenida

**nota:** _Para que funcione el servicio de envio de email de bienvenida que se pide en el item 11, se debera descomentar el codigo del archivo application.properties en la carpeta /resources_
