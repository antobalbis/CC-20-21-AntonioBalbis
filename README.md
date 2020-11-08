# Easy Warehouse

## Descripción

Este proyecto consiste en el desarrollo de un sistema de ayuda a la gestión de un almacen.

En un almacen tenemos varias personas trabajando, cada una de estas personas tiene una serie de tareas,distintas áreas de trabajo y necesitan una serie de recursos, normalmente compartidos, para poder realizar su trabajo, la colaboración de distintas áreas de trabajo o se necesitan varias personas para poder llevar a cabo una tarea.

Se propone el desarrollo de un sistema para la gestión de estos aspectos que permita a los trabajadores del almacen solventar estos asuntos con mayor facilidad.

En principio vamos a tener los departamentos de logística, mantenimiento y envíos.

## Descripción de la arquitectura

Se va a usar una arquitectura basada en microservicios, ya que vamos a tener las distintas funciones de la aplicación separadas y van a ser independientes unas de otras, por ejemplo, tendremos el informe sobre una máquina averiada como un servicio diferente al de informar del uso de una máquina.

Se ha decidido el uso de esta arquitectura ya que no todos los departamentos van a necesitar acceder a los mismos servicios ni a la misma información, ni todos los almacenes tienen los mismos requisitos y de esta forma se puede ofrecer únicamente lo que se necesite.

## Historias de usuario

[[HU1](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/6)] Como cliente quiero poder buscar la información meteorológica a partir del nombre del lugar.

[[HU2](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/7)] Como administrador quiero poder acceder a la información del sitio a partir del nombre del lugar.

[[HU3](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/8)] Como adminsitrador quiero poder enviar los datos meteorológicos manualmente.

[[HU4](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/9)] Como cliente quiero que la información esté actualizada.

[[HU5](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/10)] Como cliente quiero obtener las zonas que se encuentran en riesgo por viento, tormenta o temperatura.

[[HU6](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/11)] Como cliente quiero poder consultar los lugares que cumplan con una serie de condiciones climáticas.

[[HU7](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/12)] Como cliente quiero poder realizar la consulta a través de un mapa interactivo.

## Planificación del proyecto
La planificación del proyecto se encuentra en este [enlace](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/planificacion.md)

## Clases creadas
Se han creado las clases [Cliente](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Cliente.scala), [Automatizador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Actualizador.scala), [Controlador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Controlador.scala) y [Localidad](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Localidad.scala) y se han implementado los setters y getters de Localidad y se ha comprobado que compila.

![Imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/compilacion.png)



## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)