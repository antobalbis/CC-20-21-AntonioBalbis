[![Build Status](https://app.travis-ci.com/antobalbis/easywarehouse.svg?branch=main)](https://app.travis-ci.com/antobalbis/easywarehouse) [![CircleCI](https://circleci.com/gh/antobalbis/easywarehouse/tree/main.svg?style=svg)](https://circleci.com/gh/antobalbis/easywarehouse/tree/main) [![automated-update](https://github.com/antobalbis/easywarehouse/actions/workflows/autoupdate.yml/badge.svg?branch=main&event=push)](https://github.com/antobalbis/easywarehouse/actions/workflows/autoupdate.yml)
# Easy Warehouse

## Descripción

Este proyecto consiste en el desarrollo de un sistema de gestión del trabajo en un almacén, el objetivo es mejorar la coordinación y facilitar la comunicación entre los trabajadores del almacen.

En un almacén en el que hay varias personas trabajando aparecen situaciones en las que es necesario la coordinación entre los distintos trabajadores, para evitar o reducir las situaciones en las que un trabajador se queda esperando, o bien la ayuda de otro trabajador, el uso de algún recurso del almacen o que se le asigne alguna tarea. Puede aparecer, por ejemplo, la necesidad de hacer uso de una máquina que está en uso por otro trabajador, o tenga que realizarse una tarea para la que se necesita más de un trabajador.

Se propone el desarrollo de un sistema que permita a los trabajos coordinarse en la realización de sus tareas de forma que favorezca tanto el trabajo individual como el trabajo en equipo.

En principio vamos a tener los departamentos de logística, mantenimiento y dirección.

## Justificación del framework elegido

Para este proyecto se han evaluado tres frameworks: Cask, Play y Akka. Play y Akka tienen la ventaja de ser dos frameworks bastante usados y, por tanto, es difícil encontrarse con un problema que no haya tenido alguién antes, pero a la hora de definir las rutas e implementar y usar el framework considero que cask es más sencillo, ya que su sencillez es precisamente uno de las características que se buscan.

Las rutas en cask se definen encima del método al que van a referenciar añadiendo @cask.metodo_http(ruta), mientras que para Akka se definen como una sucesión de "paths" y en play las rutas son definidas en un archivo aparte que indican el método http, la ruta y el método al que llaman, es precisamente por esta simplicidad a la hora de definir y de usar el framework por lo que me decanté por cask, ya que para esta aplicación no vamos a tener requisitos especiales de rendimiento.

## Diseño del API

Para la implementación de la API se han usado 3 métodos de HTTP:
- POST: Para la creación de nuevos objetos.
- GET: Para obtener datos del servicio.
- POST: Para la modificación de los datos actuales.

Para indicar el método de HTTP a nuestra función lo único que tenemos que añadir es @cask.get(ruta), @cask.post(ruta) o @cask.put(ruta), pero además cask tiene el método postJson que recibe e interpreta los datos en formato json y asocia los valores a las variables de entrada del método, quedando de la siguiente manera.

```
@cask.postJson("/addMaquina")
	def addMaquina(userID_ : Seq[Int], id_ : Seq[Int], nombre : String)
```
Para GET y PUT es necesario parsear el json. Para eso se ha hecho uso de la biblioteca upickle.

Se ha prescindido del método delete debido a que en los método en los que se eliminan datos se envía al menos el id del objeto a eliminar y el id del trabajador que pide eliminarlos, por tanto, en su lugar se ha usado POST también.

Para cada una de las HU implementadas se ha creado una ruta con un nombre relacionable con esta HU.

|HU|ruta|método|descripción
|--|--|--|--|
|[HU13](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/27)|/addMaquina|POST| Como trabajador de logística quiero poder añadir una máquina.|
|[HU14](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/35) |/rmMaquina  | POST | Como trabajador de logística quiero poder eliminar una máquina. |
| [HU1](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/6) |/getLista/:id | GET | Como trabajador del almacén quiero conocer si hay una máquina disponible. |
| [HU2](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/7) |/usarMaquina | PUT | Como trabajador del almacén quiero informar del uso de una máquina.  |
| [HU15](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/36) |/dejarMaquina | PUT | Como trabajador quiero informar del fin del uso de una máquina.  |
| [HU3](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/9) |/cambiarEstado | PUT |Como trabajador de mantenimiento quiero informar del estado de la reparación de una máquina. |
| [HU4](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/37) |/averiar | PUT | Como trabajador del almacén quiero informar de una avería. |

Así quedaría para las Historias de Usuario que se implementan en [ControladorMaquinas](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/src/ControladorMaquinas.scala). De esta forma vemos que para aquellas historias de usuario que se crea o se elimine un elemento del servicio se usa el método POST de HTTP, cuando se modifica PUT y cuando se obtiene GET.

|HU|ruta|método| descripción |
|--|--|--|--|
| [HU5](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/25) |/getLista/:id | POST | Como trabajador del almacén quiero poder solicitar la colaboración de otros trabajadores. |
| [HU6](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/26) |/getLista/:id | PUT | Como trabajador quiero poder responder a la petición de colaboración de otro trabajador. |

La tabla de arriba muestra los métodos usados para las historias de usuario de [ControladorSolicitudes](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/src/ControladorSolicitud.scala).

Para cada uno de estos métodos se han añadido unos logs que informan de si todo ha salido bien o si algo ha fallado y cual ha sido la causa de ese fallo. Para estos logs se han usado los que ya trae cask. Para añadir un log solo tiene que llamar al método debug de la clase Logger y añadir el string que quieres que imprima por pantalla. El resultado se muestra en la siguiente imagen.

![Log](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/logs.png)

Para adaptar los tests a la api se ha hecho uso del objeto requests de cask. Para usarlo se usan los métodos post, get o put de este objeto seguidos de la ruta y si se requiere de payload se le añade al parámetro data con un string en formato JSON. No es necesario que sea en formato JSON pero es el formato que se ha usado, como se muestra a continuación.

```
requests.post(s"$host/addMaquina",
      data = """{"userID_": [7], "id_":[8], "nombre": "maquina"}""")
```

Para evaluar el resultado se ha comprobado si se ha aplicado o no el efecto esperado. Algunas de las respuestas del servicio han sido también en formato JSON y por tanto para evaluar el resultado de los tests ha sido necesario parsearlo con el mismo objeto que se ha comentado.

## Tareas

Para facilitar la ejecución de las tareas se ha creado un makefile con los comandos build,install y test. Para build e install se ejecutará el comando eWarehouse[3.0.1].compile y para test eWarehouse[3.0.1].test. **El Makefile se ha creado para la ejecución de estas tareas no porque se esté usando Makefile como gestor de tareas**, el gestor de tareas es mill.

## Resultado de los tests

### Paso de tests en travis

![tests_travis](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/travis_microservicio.png)

### Paso de tests en circleci

![tests_circleci](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/circle_ms.png)

### Paso de tests en contenedor docker

![tests_docker](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/docker_hub_ms.png)

### Resultados de CI

![CI](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/ci_ms.png)

## trabajo previo
- [Arquitectura](https://github.com/antobalbis/easywarehouse/blob/main/docs/arquitectura.md).
- [Planificación](https://github.com/antobalbis/easywarehouse/blob/main/docs/planificacion.md).
- [Tests](https://github.com/antobalbis/easywarehouse/blob/main/docs/pruebas.md).
- [Contenedores](https://github.com/antobalbis/easywarehouse/blob/main/docs/contenedores.md).
- [Integración continua](https://github.com/antobalbis/easywarehouse/blob/main/docs/cintegration.md).

## Código del proyecto

Se han creado las clases [Maquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Maquina.scala), [Trabajador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Trabajador.scala) , [Solicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Solicitud.scala), [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) y [ControladorSolicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorSolicitud.scala) y los enumerados [Departamento](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Departamento.scala) y [EstadoMaquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/EstadoMaquina.scala).

Los tests se encuentran en [testControladorMaquinas](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/test/src/testControladorMaquina.scala) y [testControladorSolicitud](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/test/src/testControladorSolicitud.scala).

Los ficheros de ci son: travis: [.travis.yml](https://github.com/antobalbis/easywarehouse/blob/main/.travis.yml), circleci: [config.yml](https://github.com/antobalbis/easywarehouse/blob/main/.circleci/config.yml) y github actions: [autoupdate.yml](https://github.com/antobalbis/easywarehouse/blob/main/.github/workflows/autoupdate.yml).

## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)