package eWarehouse

import org.scalatest.funsuite.AnyFunSuite

class CMtests extends AnyFunSuite{
  var cm : ControladorMaquinas = new ControladorMaquinas()

  for(i <- 0 to 4){
    cm.addMaquina(i, "maquina-"+i)
  }

  test("Lista de máquinas debe tener 4 elementos") {
    assert(cm.listaMaquinas.length == 5)
  }
}
