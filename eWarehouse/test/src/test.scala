package eWarehouse

import org.scalatest.funsuite.AnyFunSuite

class CMtests extends AnyFunSuite{
  var cm : ControladorMaquinas = new ControladorMaquinas()
  var lt : List[Trabajador] = List()
  lt = new Trabajador(0, "aa", Departamento.DIRECCION) :: lt
  lt = new Trabajador(1, "ab", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(2, "ac", Departamento.MANTENIMIENTO) :: lt

  cm.listaTrabajadores = lt

  for(i <- 0 to 4){
    cm.addMaquina(1, i, "maquina-"+i)
  }

  //TEST ADD MAQUINAS
  test("Lista de máquinas debe tener 5 elementos") {
    val n = cm.listaMaquinas.length
    cm.addMaquina(1, 5, "maquina-5")
    assert(cm.listaMaquinas.length == n+1)
  }

  test("No se añade una máquina con id repetido"){
    val n = cm.listaMaquinas.length
    cm.addMaquina(1, 3, "maquina_fallo")
    assert(cm.listaMaquinas.length == n)
  }

  test("Comprobar que no se añade una máquina si el usuario no existe o no es del departamento de logística."){
    cm.addMaquina(userID = 7, id = 6, "maquina 5")
    assert(!cm.listaMaquinas.exists(m => m.ID == 6))
  }

  //TEST LISTA MAQUINAS
  test("No se muestra ninguna máquina con estado distinto a FUNCIONANDO"){
    cm.listaMaquinas(3).estado = EstadoMaquina.PENDIENTE
    assert(!cm.getListaMaquinas(0).contains(cm.listaMaquinas(3)))
  }

  test("Comprobar que se muestran todas las máquinas con estado FUNCIONANDO"){
    assert(cm.getListaMaquinas(0).exists(m => m.estado.equals(EstadoMaquina.FUNCIONANDO)))
  }

  test("Comprobar que si el usuario no existe el resultado es una lista vacía"){
    assert(cm.getListaMaquinas(-1).length == 0)
  }

  //TESTS ELIMINA MAQUINA
  test("EL NUMERO DE MAQUINAS SE REDUCE EN UNO"){
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

  test("Comprobar que si el estado de la máquina es distinto a FUNCIONANDO el resultado es false"){
    val index = cm.listaMaquinas.indexWhere(m => m.estado != EstadoMaquina.FUNCIONANDO)
    assert(!cm.usarMaquina(cm.listaMaquinas(index).ID, userID))
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

  test("Comprobar que el estado de la máquina con el id indicado cambia al seleccionado"){
    cm.cambiarEstadoMaquina(userID, 5, EstadoMaquina.PENDIENTE)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 5)).estado.equals(EstadoMaquina.PENDIENTE))
  }

  test("Comprobar que si el trabajador no es de mantenimiento el estado no cambia."){
    val id = cm.listaTrabajadores.indexWhere(t => !t.departamento.equals(Departamento.LOGISTICA))
    cm.cambiarEstadoMaquina(userID, 0, EstadoMaquina.PENDIENTE)
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 0)).estado.equals(EstadoMaquina.PENDIENTE))
  }
}
