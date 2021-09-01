package eWarehouse

import org.scalatest.funsuite.AnyFunSuite

class CSTest extends AnyFunSuite{
  var cs : ControladorSolicitud = new ControladorSolicitud
  var lt : List[Trabajador] = List()
  lt = new Trabajador(0, "aa", Departamento.DIRECCION) :: lt
  lt = new Trabajador(1, "ab", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(2, "ac", Departamento.MANTENIMIENTO) :: lt

  cs.listaTrabajadores = lt

  for(i <- 0 to 4){
    cs.addSolicitud(1, i, "primera solicitud", 3)
  }

  test("Comprobar que la solicitud se añade a la lista"){
    assert(cs.listaSolicitudes.length == 5)
  }

  test("Comprobar que si el trabajador no existe no se añade la solicitud"){
    cs.addSolicitud(5, 10, "solicitud falsa", 3)
    assert(!cs.listaSolicitudes.exists(s => s.ID == 10))
  }

  test("Comprobar que si el trabajador no es del departamento LOGISTICA no se añade la solicitud"){
    cs.addSolicitud(0, 10, "solicitud falsa", 3)
    assert(!cs.listaSolicitudes.exists(s => s.ID == 10))
  }

  test("Comprobar que si el id existe no se añade la solicitud"){
    cs.addSolicitud(1, 2, "solicitud falsa", 3)
    assert(!cs.listaSolicitudes.exists(s => s.ID == 10))
  }
}