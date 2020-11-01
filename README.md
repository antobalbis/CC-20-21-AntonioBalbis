# Quick Weather Gran Canaria

## Descripción

En este proyecto va a consistir en el desarrollo de una plataforma que informe en tiempo real sobre las condiciones meteorológicas en la isla de Gran Canaria, por tanto, el problema a resolver consiste en acceder en tiempo real a la información meteorológica de la isla de Gran Canaria. Para ello, en la web del gobierno [datos.gob.es](https://datos.gob.es/catalogo/l03380010-datos2) se publican los datos recogidos en distintos formatos. Ofrecer esta información ayuda a, por ejemplo, planificar un fin de semana o cambiar la planificación de un viaje a otro día en el que haga mejor tiempo.

La funcionalidad básica sería la de, a partir de la búsqueda de una zona de la isla de Gran Canaria ofrecer la información meteorológica de dicha zona en el momento de la búsqueda.

## Descripción de la arquitectura

Para este proyecto vamos a usar una arquitectura en capas, esta arquitectura es una arquitectura cliente-servidor en la que las capas cliente, la capa controlador y la capa de acceso a datos están separadas. Esta arquitectura nos proporciona un software flexible en la que en caso de ser necesario una modificación en alguna de las capas esta solo afecta a la capa en cuestión.

Para nuestro proyecto tendremos 3 capas, la capa de presentación con la interfaz de usuario, en la capa de aplicación tendremos las peticiones de datos del cliente y las peticiones de datos y de actualización de datos del administrador y por último la capa de acceso a datos que se encargará de recibir las peticiones de cliente y administrador y tanto de acceder como de actualizar los datos.

## Historias de usuario

[[HU1](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/6)] Como cliente quiero poder buscar la información meteorológica a partir del nombre del lugar.

[[HU2](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/7)] Como administrador quiero poder acceder a la información del sitio a partir del nombre del lugar.

[[HU3](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/8)] Como adminsitrador quiero poder enviar los datos meteorológicos manualmente.

[[HU4](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/9)] Como cliente quiero que la información esté actualizada.

[[HU5](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/10)] Como cliente quiero obtener las zonas que se encuentran en riesgo por viento, tormenta o temperatura.

[[HU6](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/11)] Como cliente quiero poder consultar los lugares que cumplan con una serie de condiciones climáticas.

[[HU7](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/12)] Como cliente quiero poder realizar la consulta a través de un mapa interactivo.

## Planificación del proyecto

Este proyecto se va a desarrollar a lo largo de distintos hitos, los hitos en los que se va a dividir el proyecto son los siguientes.

#### Hito 1: Consulta de datos.
En este primer hito se desarrollará la funcionalidad asociada a la consulta de datos meteorológicos, este va a consistir en la funcionalidad más básica del proyecto que se está desarrollando que consiste en enviar y consultar los datos meteorológicos por parte de un administrador y la consulta de los datos por parte del cliente.

Historias de usuario: HU1, HU2 y HU3.
Tiempo planificado: 1 semana.

#### Hito 2: Automatización del proceso
En este segundo hito se va a disponer de un proceso de obtención y actualización de los datos automatizado. Por tanto ahora se actualizarán los datos de forma automática sin necesidad de intervención del administrador.

Historias de usuario: HU4.
Tiempo planificado: 1 semana.

#### hito 3: Sistema de advertencia y recomendación
En este hito se va a implementar una funcionalidad que permita al cliente obtener que lugares van a tener un tiempo cuyos parámetros se salgan de lo normal y puedan estar en alerta por viento, lluvia, nieve o temperatura.

Historias de usuario: HU5 y HU6.
Tiempo planificado: 1 semana.

#### hito 4: Implementación de la interfaz de usuario
En este hito se va a implementar la interfaz de usuario en el lado del cliente que va a incluir un mapa interactivo para la consulta de los datos meteorológicos.

Historias de usuario: HU7.
Tiempo planificado: 2 semanas.

## Clases creadas
Se han creado las clases [Cliente](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Cliente.scala), [Automatizador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Actualizador.scala), [Controlador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Controlador.scala) y [Localidad](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Localidad.scala) y se han implementado los setters y getters de Localidad y se ha comprobado que compila.

![Imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/compilacion.png)



## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)