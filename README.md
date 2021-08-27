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

Se han creado las clases [Maquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Maquina.scala), [Trabajador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Trabajador.scala) , [Solicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Solicitud.scala), [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) y [ControladorSolicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorSolicitud.scala) y los enumerados [Departamento](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Departamento.scala) y [EstadoMaquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/EstadoMaquina.scala).

A continuación se muestra una imagen con el resultado de la compilación de las clases anteriores.

![Imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/compilacion2.png)

La estructura del proyecto se va a modificar en las entregas siguientes.

# Gestor de Tareas

Algunos de los gestores de tareas que encontramos para Scala sonMaven, Gradle o Mill. El gestor de tareas que se ha elegido para este proyecto es Mill.

La elección de Mill viene porque utiliza la misma sintaxis que Scala, es un gestor de tareas a diferencia de SBT y además resulta más sencillo de utilizar que Maven o Gradle, permitiéndonos definir las herramientas y la versión como variables en Scala y nos permite definir las dependencias agregándolas en una única línea a la variable ivyDeps, esto resulta más sencillo y más rápido de identificar que usando Maven o Gradle.

# Marco de pruebas

Tenemos varios marcos de pruebas para scala, entre ellos destacan uTest, ScalaTest y Specs2. Para el proyecto que se está desarrollando valdría cualquiera de ellos, pero se ha optado por ScalaTest porque está implementado en Scala y los tests se definen como métodos en Scala de una forma que me ha resultado más intuitiva que los otros marcos de pruebas.

ScalaTest tiene distintos estilos de tests, en este caso me he decantado por FunSuite.


# Biblioteca de aserciones

La biblioteca de aserciones que vamos a usar es Assertions, de ScalaTest, ya que este es el marco de pruebas que se va a utiilizar se ha optado por usar su propia librería de aserciones que ofrece lo necesario para realizar los tests unitarios que se van a requerir en este proyecto.

#Configuración del gestor de tareas

[Archivo de configuración del gestor de tareas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/build.sc).

Para la configuración del gestor de tareas se ha creado un objeto, el nombre del objeto viene dado por el directorio en el que se encuentra el código. La estructura de directorios tiene que ser la siguiente:

|-- build.sc (archivo de configuración de mill)
|-- eWarehouse
  |-- src
  | |-- < código en scala >
  |-- test
     |--src
        |-- < tests >

Luego se define la versión de scala y dentro del objeto eWarehouse se define el objeto Tests y le indicamos las dependencias de los test y el Framework que se va a usar. En este caso, como se ha decidido usar scalatest, se añaden las dependencias de scalatest y la versión, en este caso la versión 3.1.1 y luego se indica el framework en la variable testFrameworks.


```
import mill._, scalalib._

object eWarehouse extends ScalaModule{
  def scalaVersion = "2.13.2"
  object test extends Tests{
    def ivyDeps = Agg(ivy"org.scalatest::scalatest:3.1.1")
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}
```
## Tests

Los tests se encuentran en el siguiente enlace: [test.scala](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/test/src/test.scala).
La clase que evalúan los tests es [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala)

A continuación se muestre el resultado de los tests.

![Imagen tests](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/paso_tests_p2.png)

## Elección y justificación de la imagen base

Para la elección de la imagen base de nuestro contenedor Docker se han considerado distintas distribuciones de linux, que se muestran en la siguiente captura.

![Imágenes docker](https://github.com/antobalbis/CC-20-21-antoniobalbis/tree/main/docs/imagenes/captura_images.png)

La imagen que ha sido elegida es Alpine, ya que tiene un tamaño de 5,6MB mientras que el resto de distribuciones tienen un tamaño mucho mayor, siendo ubuntu la más ligera con 72,8MB. En un principio se pensó en usar la propia imagen que tienen los desarrolladores de mill en su [página](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html), pero esto se descartó ya que usan una imagen de OpenJDK que tiene Debian como imagen base, y se consideró que no era la mejor opción.



## Dockerfile

Enlace al [Dockerfile](https://github.com/antobalbis/easywarehouse/blob/main/Dockerfile)


## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)