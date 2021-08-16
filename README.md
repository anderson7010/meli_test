# Mutant Analyzer - MeLi Test

Proyecto solicitado por Magneto para detectar si alguien es mutante en base a su secuencia de ADN.

## - Funcionalidades
* Servicio de validación de secuencias de ADN.
* Servicio de consulta de estadísticas de secuencias de ADN validadas.

## - Tecnologias utilizadas
* Clean architecture
* Java
* Spring Boot
* Docker
* Amazon EC2
* Amazon RDS
* PostgreSQL
* Swagger
* JUnit
* Mockito
* Jacoco
* Lombok
* Mapstruct
* Git

# Clean Architecture

Para explicar la arquitectura se comenzará por los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## - Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## - Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## - Infrastructure

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## - Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

# API Documentation
Se implementó Swagger para la documentación del API desarrollada.

# Deployment
Se desplegó la aplicación en una instancia ECS de AWS con URL http://ec2-3-18-108-167.us-east-2.compute.amazonaws.com

# Testing:

* Los casos de uso y los entry points cuentan con sus pruebas unitarias.

## - Coverage
Se implementó jacoco para generar reportes de cobertura de código. Dicha tarea se ejecuta con un clean build.

### Desarrollado por

Anderson Yahir Vega Castillo [linkedIn](www.linkedin.com/in/ing-anderson-yahir-vega-castillo)
