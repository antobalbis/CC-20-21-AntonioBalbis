# Easy Warehouse

## Descripción

Este proyecto consiste en el desarrollo de un sistema de gestión del trabajo en un almacén, el objetivo es mejorar la coordinación y facilitar la comunicación entre los trabajadores del almacen.

En un almacén en el que hay varias personas trabajando aparecen situaciones en las que es necesario la coordinación entre los distintos trabajadores, para evitar o reducir las situaciones en las que un trabajador se queda esperando, o bien la ayuda de otro trabajador, el uso de algún recurso del almacen o que se le asigne alguna tarea. Puede aparecer, por ejemplo, la necesidad de hacer uso de una máquina que está en uso por otro trabajador, o tenga que realizarse una tarea para la que se necesita más de un trabajador.

Se propone el desarrollo de un sistema que permita a los trabajos coordinarse en la realización de sus tareas de forma que favorezca tanto el trabajo individual como el trabajo en equipo.

En principio vamos a tener los departamentos de logística, mantenimiento y dirección.

## Arquitectura del proyecto

La arquitectura del proyecto se encuentra en el siguiente [enlace](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/arquitectura.md).

## Historias de usuario y planificación

Las historias de usuario y la planificación del proyecto se encuentran en el siguiente [enlace](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/planificacion.md).

## Clases creadas

Se han creado las clases [Maquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/src/Maquina.scala), [Trabajador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Trabajador.scala) , [Solicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Solicitud.scala), [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) y [ControladorSolicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorSolicitud.scala) y los enumerados [Departamento](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Departamento.scala) y [EstadoMaquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/EstadoMaquina.scala).

A continuación se muestra una imagen con el resultado de la compilación de las clases anteriores.

![Imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/compilacion2.png)

La estructura del proyecto se va a modificar en las entregas siguientes.

# Gestor de Tareas

Algunos de los gestores de tareas que encontramos para Scala son Scala Build Tool (SBT), Maven, Gradle o Mill. El gestor de tareas que se va a usar en el proyecto es Mill.

La elección de Mill viene porque utiliza la misma sintaxis que Scala, es un gestor de tareas a diferencia de SBT y además resulta más sencillo de utilizar que Maven o Gradle, permitiéndonos definir las herramientas y la versión como variables en Scala.

# Marco de pruebas

El marco de pruebas elegido para este proyecto es ScalaTest. Se ha elegido porque está implementado en Scala y, por tanto, los tests se definen como métodos en Scala y además cuenta con su propia biblioteca de aserciones, Assertions.

# Biblioteca de aserciones

La biblioteca de aserciones que vamos a usar es Assertions, de ScalaTest, ya que este es el marco de pruebas que se va a utiilizar se ha optado por usar su propia librería de aserciones que ofrece lo necesario para realizar los tests unitarios que se van a requerir en este proyecto.


## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)