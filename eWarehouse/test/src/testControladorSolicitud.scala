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
    cs.addSolicitud(-1, 10, "solicitud falsa", 3)
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


  lt = new Trabajador(3, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(4, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(5, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(6, "ac", Departamento.LOGISTICA) :: lt
  cs.listaTrabajadores = lt

  test("Comprobar que el trabajador se añade a la lista de trabajadores de la solicitud y el número de personas disminuye en 1."){
    val nPersonas = cs.getSolicitudByID(0).restantes
    cs.apuntarseSolicitud(3, 0)
    assert(cs.getSolicitudByID(0).trabajadores.exists(t => t == 3))
    assert(nPersonas - 1 == cs.getSolicitudByID(0).restantes)
  }

  test("Comprobar que si el trabajador no existe no se añade a la lista y el número de personas se mantiene."){
    cs.apuntarseSolicitud(-1, 0)
  }

  test("Comprobar que si el trabajador ya está en la lista el número de personas restantes se mantiene."){
    val nPersonas = cs.getSolicitudByID(0).restantes
    cs.apuntarseSolicitud(3, 0)
    assert(nPersonas == cs.getSolicitudByID(0).restantes)
  }

  test("Comprobar que si se ha alcanzado el número de trabajadores no se añada a otro trabajador."){
    cs.apuntarseSolicitud(4, 0)
    cs.apuntarseSolicitud(5, 0)
    //max apuntados
    val nPersonas = cs.getSolicitudByID(0).restantes
    cs.apuntarseSolicitud(6, 0)
    assert(nPersonas == cs.getSolicitudByID(0).restantes)
    assert(!cs.getSolicitudByID(0).trabajadores.exists(t => t == 6))
  }

  test("Comprobar que si el trabajador no es de logística no se añade a la lista."){
    val nPersonas = cs.getSolicitudByID(1).restantes
    cs.apuntarseSolicitud(0, 1)
    assert(!cs.getSolicitudByID(1).trabajadores.exists(t => t == 0))
    assert(nPersonas == cs.getSolicitudByID(1).restantes)
  }

  test("Comprobar que si el id es el id del solicitante no se añade a la lista."){
    val nPersonas = cs.getSolicitudByID(1).restantes
    cs.apuntarseSolicitud(1, 1)
    assert(nPersonas == cs.getSolicitudByID(1).restantes)
    assert(!cs.getSolicitudByID(1).trabajadores.exists(t => t == 1))
  }
}