# Historias de usuario y planificación del proyecto.

## Planificación del proyecto

Este proyecto se va a desarrollar a lo largo de 3 hitos, estos hitos son los siguientes.

#### Hito 1: Sistema de trabajo colaborativo.

Al finalizar este hito se va a tener un sistema que va a permitir a los trabajadores del almacén coordinarse en la realización del trabajo, de forma que puedan pedir colaboración o solicitar el uso de recursos.

Historias de usuario: 

- [[HU13](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/27)] Como trabajador de logística quiero poder añadir una máquina.
- [[HU14](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/35)] Como trabajador de logística quiero poder eliminar una máquina.
  - Depende de HU13.
- [[HU1](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/6)] Como trabajador del almacén quiero conocer si hay una máquina disponible.
- [[HU2](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/7)] Como trabajador del almacén quiero informar del uso de una máquina.
  - Depende de HU1.
- [[HU15](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/36)] Como trabajador quiero informar del fin del uso de una máquina.
  - Depende de HU2.
- [[HU3](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/9)] Como trabajador de mantenimiento quiero informar del estado de la reparación de una máquina.
- [[HU4](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/37)] Como trabajador del almacén quiero informar de una avería.
- [[HU5](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/25)] Como trabajador del almacén quiero poder solicitar la colaboración de otros trabajadores.
- [[HU6](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/26)] Como trabajador quiero poder responder a la petición de colaboración de otro trabajador.
  - Depende de HU5.



#### Hito 2: Sistema de planificación de tareas.

Al finalizar este hito vamos a tener agregado al hito anterior la posibilidad de crear tareas por parte de los encargados para que sean realizadas por los trabajadores del almacén.

Historias de usuario: 

- [[HU7](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/20)] Como encargado quiero poder asignar tareas a los departamentos.

- [[HU8](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/21)] Como trabajador del almacén quiero conocer las tareas asignadas a mi departamento.
  - Depende de HU7.
- [[HU9](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/24)] Como encargado quiero asignar recursos a una tarea.
  - Depende de HU7.



#### Hito 3: Sistema de gestión de almacenamiento.

Al finalizar este hito vasmos a tener agregado al hito anterior la capacidad de gestionar el inventario del almacén para que pueda ser realizada por los trabajadores y encargados de la logística de este.

Historias de usuario:

- [[HU10](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/23)] Como trabajador de logística quiero poder añadir un nuevo producto.
- [[HU11](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/11)] Como trabajador de logística quiero conocer el inventario disponible.
- [[HU12](https://github.com/antobalbis/CC-20-21-antoniobalbis/issues/12)] Como trabajador de logística quiero recoger el envío y recepción de productos.
  - Depende de HU10.


## Código del proyecto

Se han creado las clases [Maquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Maquina.scala), [Trabajador](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Trabajador.scala) , [Solicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Solicitud.scala), [ControladorMaquinas](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorMaquinas.scala) y [ControladorSolicitud](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/ControladorSolicitud.scala) y los enumerados [Departamento](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/Departamento.scala) y [EstadoMaquina](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/eWarehouse/src/EstadoMaquina.scala).

A continuación se muestra una imagen con el resultado de la compilación de las clases anteriores.

![Imagen 1](https://github.com/antobalbis/CC-20-21-antoniobalbis/blob/main/docs/imagenes/compilacion2.png)

