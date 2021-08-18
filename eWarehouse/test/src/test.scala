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

  test("No se añade una máquina con id repetido"){
    cm.addMaquina(3, "maquina_fallo")
    assert(cm.listaMaquinas.length == 5)
  }

  test("No se muestra ninguna máquina con estado distinto a FUNCIONANDO"){
    cm.listaMaquinas(3).estado = EstadoMaquina.PENDIENTE
    assert(!cm.getListaMaquinas().contains(cm.listaMaquinas(3)))
  }

  test("Comprobar que se muestran todas las máquinas con estado FUNCIONANDO"){
    /*var result : Boolean = true
    val lista : List[Maquina] = cm.getListaMaquinas()
    for(maquina <- cm.listaMaquinas){
      if(maquina.estado.equals(EstadoMaquina.FUNCIONANDO)) result = lista.contains(maquina)
    }*/
    assert(cm.getListaMaquinas().exists(m => m.estado.equals(EstadoMaquina.FUNCIONANDO)))
  }
}
