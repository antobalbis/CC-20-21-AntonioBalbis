## Configuración de Travis CI

Travis es un servicio de integración continua para probar proyectos de Github o Bitbucket. Lo primero que necesitamos para poder usar Travis en nuestro proyecto es registrarnos con nuestra cuenta de Github y luego dar acceso a Travis a nuestro repositorio.

Travis lleva a cabo la integración continua a partir del fichero de configuración [.travis.yml](https://github.com/antobalbis/easywarehouse/blob/main/.travis.yml). En el que se especificarán los pasos que llevará a cabo para la comprobación del proyecto. En este caso concreto se va a usar la integración con Travis para testear la aplicación con distintas versiones de Scala.

Lo primero que escribimos en el archivo de configuracion será el lenguaje usado y las versiones del mismo. En este caso se han elegido las versiones 3.0.1, por ser la última versión de scala y la versión 2.12.1 por ser la versión más antigua compatible con lav ersión de zinc de nuestro gestor de tareas.

Luego se ha escrito la versión de JDK que se va a usar. En este caso ha isdo la 11 ya que es la versión que se ha elegido para nuestro contenedor Docker.

El siguiente paso es instalar las dependencias, en este caso se tiene que instalar mill, la versión de mill es la 0.9.9 por ser la última. Por último tendremos que instalar el compilador de Scala 3, realmente este paso solo es necesario para las versiones 3.0.0 y superiores, ya que Travis usa la versión 2.12.8 por defecto. Para esto se ha tenido que descargar coursier, darle permisos de ejecución y ejecutar el comando *./coursier install scala3-compiler* para inestalar el compulador de scala 3.

El último paso es el comando para la ejecución de los tests dentro del espacio de script. Esto comprobará que la aplicación pasan todos los tests y, en caso contrario, la build fallará, marcando con una "x" roja nuestro repositorio y mandándonos un mensaje al correo de Github. La siguiente imagen muestra la salida en consola de un error en los tests en Travis.

![error travis](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/fallo_test_travis.png)

En caso de que la palicación pase los test de Travis y la configuración de Travis sea correcta se mostrará en el repositorio que ha pasado los tests.

![travis bien](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/travis_bien.png)

## Configuración de Circleci

Otra herramienta para integración continua es Circleci. Para usarla, al igual que con Travis, tenemos que registrarnos con Github y activar el repositorio en donde queremos usar circleci. Una vez que hemos hecho esto nos pedirá que creemos un archivo [config.yml](https://github.com/antobalbis/easywarehouse/blob/main/.circleci/config.yml) en la carpeta .circleci y nos dará la oportunidad de usar una plantilla.

![enlace repo](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/capturaCircleCI.png)

Para la creación de un trabajo tenemos que elegir primero donde se va a construir, en este caso se ha usado un contenedor Docker con una imagen con jdk 11 de propia de Circleci, ya que en la imagen de Docker que se ha creado para este proyecto utiliza esa versión. Para especificar la versión de scala se ha definido un parámetro con el valor 3.0.1.

Por último se definen los pasos que va a seguir Circleci. Los pasos que requieran de la ejecución de un comando o un script van precedidos por la etiqueta run. El primer paso es comprobar el contenedor, luego descargamos mill y coursier para instalar el compilador de Scala 3 y la úlimta orden será la ejecución de los tests con mill.

![fallo tests circle](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/captura_error_circle_ci.png)

De igual forma que en Travis, al no haber errores en la construcción y pasar los test se indicará en el repositorio de Github.

## Configuración de integración continua con Github Actions

La integración continua con Githubs Actions se van a usar para ejecutar los tests usando la imagen de Docker creada para el proyecto ya que todo el trabajo previo necesario ya se ha implementado. El yaml con el flujo de trabajo se encuentra [aquí](https://github.com/antobalbis/easywarehouse/blob/main/.github/workflows/autoupdate.yml).

Como ya teníamos creados los trabajos para construir y subir la imagen a Docker Hub y Github lo único que tenemos que añadir ahora es la ejecución de los tests. Para ello añadimos un nuevo paso a los trabajos a los que, con la etiqueta run, les indicamos que ejecuten la orden *docker run -t antobalbis/easywarehouse*.

Igual que en los casos anteriores, si la ejecución de los test no es positiva se indicará el error en el repositorio.

## Resultado de Integración Continua.

![resultados tests ci](https://github.com/antobalbis/easywarehouse/blob/main/docs/imagenes/paso_test_ci.png)

## Avance del proyecto

Se ha avanzado en el desarrollo de las historias de usuario [[HU5]](https://github.com/antobalbis/easywarehouse/issues/25) y [[HU6]](https://github.com/antobalbis/easywarehouse/issues/26).
Se ha cambiado el código de [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) añadiendo métodos auxiliares.
Se han añadidos los tests para las tareas relacionadas con las HU anteriores en [testControladorSolicitud](https://github.com/antobalbis/easywarehouse/blob/main/eWarehouse/test/src/testControladorSolicitud.scala).

