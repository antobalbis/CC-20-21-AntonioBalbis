package eWarehouse

import org.scalatest.funsuite.AnyFunSuite

class CMtests extends AnyFunSuite{
  var cm : ControladorMaquinas = new ControladorMaquinas()

  for(i <- 0 to 4){
    cm.addMaquina(i, "maquina-"+i)
  }

  test("Lista de máquinas debe tener 5 elementos") {
    assert(cm.listaMaquinas.length == 5)
  }

  test("No se añade una máquina con id repetido"){
    cm.addMaquina(3, "maquina_fallo")
    assert(cm.listaMaquinas.length == 5)
  }

  test("No se muestra ninguna máquina con estado distinto a FUNCIONANDO"){
    cm.listaMaquinas(3).estado = EstadoMaquina.PENDIENTE
    assert(!cm.getListaMaquinas().contains(cm.listaMaquinas(3)))
  }

  test("Comprobar que se muestran todas las máquinas con estado FUNCIONANDO"){
    assert(cm.getListaMaquinas().exists(m => m.estado.equals(EstadoMaquina.FUNCIONANDO)))
  }

  //TESTS USO MÄQUINA
  test("El estado de la máquina con el id especificado cambia a true"){
    assert(cm.usarMaquina(2, 0))
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 2)).isBeingUsed)
  }

  test("Comprobar que si el valor de isBeingUsed es true el resultado es false"){
    assert(!cm.usarMaquina(2, 0))
  }

  test("Comprobar que si la máquina no existe el resultado es false"){
    assert(!cm.usarMaquina(-1, 0))
  }

  test("Comprobar que si el estado de la máquina es distinto a FUNCIONANDO el resultado es false"){
    assert(!cm.usarMaquina(cm.listaMaquinas(3).ID, 0))
  }

  test("Comprobar si el resultado es true que el id del usuario es igual al id del usuario de la máquina."){
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 2)).userID == 0)
  }

  //TESTS DEJAR MÁQUINA
  test("Comprobar que si el resultado es true el valor de isBeingUsed pasa a false"){
    assert(cm.dejarMaquina(2, 0))
    assert(!cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 2)).isBeingUsed)
  }

  test("Comprobar que si la máquina no existe el resultado de dejarMaquina es false"){
    assert(!cm.dejarMaquina(-1, 0))
  }

  test("Comprobar que si el resultado es true el valor de user id es -1"){
    assert(cm.listaMaquinas(cm.listaMaquinas.indexWhere(m => m.ID == 2)).userID == -1)
  }

  test("Comprobar que si no coincide el userID con el userID de la máquina el resultado es false"){
    cm.usarMaquina(2, 0)
    assert(!cm.dejarMaquina(2, 1))
  }
}
