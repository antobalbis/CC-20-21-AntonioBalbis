# Easy Warehouse

## Descripción

Este proyecto consiste en el desarrollo de un sistema de gestión del trabajo en un almacén, el objetivo es mejorar la coordinación y facilitar la comunicación entre los trabajadores del almacen.

En un almacén en el que hay varias personas trabajando aparecen situaciones en las que es necesario la coordinación entre los distintos trabajadores, para evitar o reducir las situaciones en las que un trabajador se queda esperando, o bien la ayuda de otro trabajador, el uso de algún recurso del almacen o que se le asigne alguna tarea. Puede aparecer, por ejemplo, la necesidad de hacer uso de una máquina que está en uso por otro trabajador, o tenga que realizarse una tarea para la que se necesita más de un trabajador.

Se propone el desarrollo de un sistema que permita a los trabajos coordinarse en la realización de sus tareas de forma que favorezca tanto el trabajo individual como el trabajo en equipo.

En principio vamos a tener los departamentos de logística, mantenimiento y dirección.

## Elección y justificación de la imagen base

Para la elección de la imagen base de nuestro contenedor Docker se han considerado distintas distribuciones de linux, que se muestran en la siguiente captura.

![Imágenes docker](https://github.com/antobalbis/CC-20-21-antoniobalbis/tree/main/docs/imagenes/captura_images.png)

Viendo los distintos tamaños de las imágenes, lo más adecuado será usar Alpine, ya que tiene un tamaño de 5,6MB mientras que el resto de distribuciones tienen un tamaño mucho mayor, siendo ubuntu la más ligera con 72,8MB. En un principio se pensó en usar la propia imagen que tienen los desarrolladores de mill en su [página](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html), pero esto se descartó ya que usan una imagen de OpenJDK que tiene Debian como imagen base, y se consideró que no era la mejor opción.

Al final se optó por usar la imagen azul/zulu-openjdk-alpine:8-jre que ya cuenta con jre 8 instalado en lugar de JDK completo, ya que usando scala no nos interesa el JDK entero. Se ha elegido la versión 8 de Java ya que en la documentación de [Scala](https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html) se recomienda usar la versión 8 o 11 y se ha elegido la 8 al ser una imagen LTS y ser más pequeña que la versión 11.


## Dockerfile

Lo primero que se ha hecho en el Dockerfile es definir la imagen base que se ha comentado anteriormente y la etiqueta con mi nombre de usuario de github y mi correo de Gmail.

Luego se ha definido una variable con la versión de Scala que se va a usar y otra con la versión del gestor de tareas mill. Se ha usado la versión 2.13.2 de scala, que es la que se ha estado usando en este proyecto hasta este momento, y la versión 0.9.9 de mill, que es la última versión que aparece en el repositorio de mill.

Luego se indica el directorio de trabajo del contenedor /app/test, y añadimos a ash el comando curl. Luego se crea un directorio para donde se va a guardar Scala, luego haciendo uso de curl se descarga scala con la versión especificada y mill de la misma forma.

Se han agrupado las instrucciones RUN en una sola para reducir la creación de capas para primero reducir el tamaño de la imagen final.

Por último se copian los archivos fuente y el archivo de configuración de mill.

Por último con la instrucción CMD se indica la orden para ejecutar los tests usando mill. Uno de los problemas que encontramos es que no se puede eliminar curl ya que es utilizado por mill.

Enlace al [Dockerfile](https://github.com/antobalbis/easywarehouse/blob/main/Dockerfile)

## Configuración de Docker Hub

Lo primero para poder subir nuestras imágenes a Docker Hub es registrarnos. Luego se crea el repositorio con el mismo nombre que el repositorio de github. Luego, para facilitar la actualización automática tendríamos que enlazar la cuenta de github y Docker Hub, pero recientemente se ha convertido en una función de pago, asñi que la actualización automática se ha tenido que llevar a cabo usando actions de github.

Para poder actualizar de forma automática la imagen en Docker Hub desde github vamos a crear dos secretos en el repositorio de github, uno con el nombre de usuario de dockerhub y otro con un PAT(Personal Access Token). Para crear el PAT vamos a las opciones de seguridad de Docker Hub y creamos un access token que se copia en el secreto de github. 

![Imagen secrets](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/dockerhub_tokens.png)

Ahora, cada vez que se haga un push en github tenemos que crear un flujo de trabajo en .github/workflows/ para que se actualice la imagen de Docker Hub. Este flujo de trabajo se va a ejecutar cada vez que se haga un push y el flujo de trabajo es el siguiente:

- comprobar repositorio -> iniciar sesión en Docker Hub (usando los secretos anteriores) -> definir un objeto buildx -> construir y publicar la imagen en Docker Hub.

Ahora cada vez que se hace un push en github se ejecuta una actions con el resultado como se muestra en la siguiente imagen.

![Imagen secrets](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/flujo_trabajo.png)

Para descargar la imagen del [repositorio](https://hub.docker.com/repository/docker/antobalbis/easywarehouse) vale con hacer *docker pull antobalbis/warehouse*.

## Hitos previos
- [Arquitectura](https://github.com/antobalbis/easywarehouse/blob/main/docs/arquitectura.md).
- [Planificación](https://github.com/antobalbis/easywarehouse/blob/main/docs/planificacion.md).
- [Tests](https://github.com/antobalbis/easywarehouse/blob/main/docs/pruebas.md).

## Código del proyecto

Se han creado las clases [Maquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Maquina.scala), [Trabajador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Trabajador.scala) , [Solicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Solicitud.scala), [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) y [ControladorSolicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorSolicitud.scala) y los enumerados [Departamento](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Departamento.scala) y [EstadoMaquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/EstadoMaquina.scala).

## Enlaces
- Enlace a ejercicios de [autoevaluación](https://github.com/antobalbis/autoevaluacion).
- Enlace a [Configuración](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/configuracion.md) de GitHub.
- Enlace a fichero de [objetivos](https://github.com/antobalbis/CC-20-21/blob/master/objetivos/antobalbis.md)