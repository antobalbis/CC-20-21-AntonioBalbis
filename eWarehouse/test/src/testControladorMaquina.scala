package eWarehouse

import io.undertow.Undertow
import upickle.default._

import org.scalatest.funsuite.AnyFunSuite

class CMtests extends AnyFunSuite{

  var cm : ControladorMaquinas = new ControladorMaquinas()
  var lt : List[Trabajador] = List()
  lt = new Trabajador(0, "aa", Departamento.DIRECCION) :: lt
  lt = new Trabajador(1, "ab", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(2, "ac", Departamento.MANTENIMIENTO) :: lt

  cm.listaTrabajadores = lt

  val server = Undertow.builder
    .addHttpListener(8081, "localhost")
    .setHandler(cm.defaultHandler)
    .build
  server.start()

  val host = "http://localhost:8081"

  for(i <- 0 to 4){
    val result = requests.post(s"$host/addMaquina",
    data = """{"userID_": [1], "id_":[""" + i + """], "nombre": "maquina"}""")
    printf(result.text())
  }

  //TEST ADD MAQUINAS
  test("Lista de máquinas debe tener 5 elementos") {
    val n = requests.get(s"$host/getNMaquinas").text()
    assert(n == "5")
  }

  test("No se añade una máquina con id repetido"){
    val result = requests.post(s"$host/addMaquina",
      data = """{"userID_": [1], "id_":[2], "nombre": "maquina"}""")
    val n = requests.get(s"$host/getNMaquinas").text()
    assert(n == "5")
  }

  test("Comprobar que no se añade una máquina si el usuario no existe o no es del departamento de logística."){
    val result = requests.post(s"$host/addMaquina",
      data = """{"userID_": [7], "id_":[8], "nombre": "maquina"}""")

    val n = requests.post(s"$host/getMaquina", data = """{"mID": [8]}""").text().toBoolean
    assert(!n)
  }

  //TEST LISTA MAQUINAS
  test("No se muestra ninguna máquina con estado distinto a FUNCIONANDO"){
    val cambio = requests.post(s"$host/cambiarEstado",
      data = """{"userID" : [2], "id" : [3], "estadoMaquina_" : "REPARACION"}""")

    val result = ujson.read(requests.get(s"$host/getListaMaquinas/1"))
    var contiene : Boolean = true
    for(i <- 0 to result.arr.size - 1){
      if(contiene) contiene = result(i)("estado").str.equals("FUNCIONANDO")
    }
    assert(contiene)
  }

  test("Comprobar que se muestran todas las máquinas con estado FUNCIONANDO"){
    val result = ujson.read(requests.get(s"$host/getListaMaquinas/1"))
    assert(result.arr.size == 4)
  }

  test("Comprobar que si el usuario no existe el resultado es una lista vacía"){
    val result = ujson.read(requests.get(s"$host/getListaMaquinas/12"))
    assert(result.arr.size == 0)
  }

  //TESTS ELIMINA MAQUINA
  /*test("EL NUMERO DE MAQUINAS SE REDUCE EN UNO"){
    val n = cm.listaMaquinas.length
    cm.deleteMaquina(1, 4)
    assert(cm.listaMaquinas.length == n-1)
  }

  test("La máquina que desaparece es la máquina con el ID correspondiente"){
    assert(!cm.listaMaquinas.exists(m => m.ID == 4))
  }

  test("El número de máquinas no decrece si el ID no existe una máquina con ese ID"){
    val n = cm.listaMaquinas.length
    cm.deleteMaquina(1, 12)
    assert(cm.listaMaquinas.length == n)
  }

  test("Comprobar que la máquina no se elimina si el usuario no existe"){
    val n = cm.listaMaquinas.length
    cm.deleteMaquina(4, 2)
    assert(cm.listaMaquinas.length == n)
  }

  test("Comprobar que la máquina no se elimina si el usuario no pertenece a logística"){
    val n = cm.listaMaquinas.length
    cm.deleteMaquina(0, 2)
    assert(cm.listaMaquinas.length == n)
  }

  //TESTS USO MAQUINA
  val id : Int = 5
  val userID : Int = 2
  test("El estado de la máquina con el id especificado cambia a true"){
    assert(cm.usarMaquina(id, userID))
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 5)).isBeingUsed)
  }

  test("Comprobar que si el valor de isBeingUsed es true el resultado es false"){
    assert(!cm.usarMaquina(id, userID))
  }

  test("Comprobar que si la máquina no existe el resultado es false"){
    assert(!cm.usarMaquina(-1, userID))
  }

  cm.addMaquina(1, 20, "maquina-"+20)
  cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 20)).estado = EstadoMaquina.PENDIENTE
  test("Comprobar que si el estado de la máquina es distinto a FUNCIONANDO el resultado es false"){
    assert(!cm.usarMaquina(20, userID))
  }

  test("Comprobar si el resultado es true que el id del usuario es igual al id del usuario de la máquina."){
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == id)).userID == userID)
  }

  test("Comprobar que si el usuario no existe o no es del departamento de logística el resultado es false"){
    assert(!cm.usarMaquina(0, 5))
  }

  //TESTS DEJAR MÁQUINA
  test("Comprobar que si el resultado es true el valor de isBeingUsed pasa a false"){
    assert(cm.dejarMaquina(id, userID))
    assert(!cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == id)).isBeingUsed)
  }

  test("Comprobar que si la máquina no existe el resultado de dejarMaquina es false"){
    assert(!cm.dejarMaquina(-1, userID))
  }

  test("Comprobar que si el resultado es true el valor de user id es -1"){
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == id)).userID == -1)
  }

  test("Comprobar que si no coincide el userID con el userID de la máquina el resultado es false"){
    assert(!cm.dejarMaquina(id, 1))
  }

  //Cambia estado máquina
  cm.addMaquina(1, 6, "maquina-6")

  test("Comprobar que el estado de la máquina con el id indicado cambia al seleccionado"){
    cm.cambiarEstadoMaquina(2, 6, EstadoMaquina.PENDIENTE)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 6)).estado.equals(EstadoMaquina.PENDIENTE))
    cm.cambiarEstadoMaquina(2, 6, EstadoMaquina.FUNCIONANDO)
  }

  test("Comprobar que si el trabajador no es de mantenimiento el estado no cambia."){
    cm.cambiarEstadoMaquina(0, 6, EstadoMaquina.PENDIENTE)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 6)).estado.equals(EstadoMaquina.FUNCIONANDO))
  }

  //Máquina deja de estar en funcionamiento
  cm.addMaquina(1, 15, "maquina-"+15)
  test("Comprobar que el estado de la máquina con el id indicado cambia a pendiente."){
    cm.averiaMaquina(0, 15)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 15)).estado.equals(EstadoMaquina.PENDIENTE))
  }

  test("Comprobar que si el estado de la máquina no es en funcionamiento no cambia."){
    cm.cambiarEstadoMaquina(2, 15, EstadoMaquina.REPARACION)
    cm.averiaMaquina(0, 15)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 15)).estado.equals(EstadoMaquina.REPARACION))
  }

  test("Comprobar que no cambia el estado si el id del trabajador no existe."){
    cm.cambiarEstadoMaquina(2, 15, EstadoMaquina.FUNCIONANDO)
    cm.averiaMaquina(-1, 15)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 15)).estado.equals(EstadoMaquina.FUNCIONANDO))
  }*/

}