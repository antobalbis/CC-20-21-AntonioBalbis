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

Los tests se encuentran en el siguiente enlace: [test.scala](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/test/src/testControladorMaquina.scala).
La clase que evalúan los tests es [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala)

A continuación se muestre el resultado de los tests.

![Imagen tests](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/paso_tests_p2.png)

