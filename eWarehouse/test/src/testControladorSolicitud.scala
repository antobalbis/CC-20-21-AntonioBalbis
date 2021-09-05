package eWarehouse

import io.undertow.Undertow
import org.scalatest.funsuite.AnyFunSuite

class CSTest extends AnyFunSuite{
  var cs : ControladorSolicitud = new ControladorSolicitud
  var lt : List[Trabajador] = List()
  lt = new Trabajador(0, "aa", Departamento.DIRECCION) :: lt
  lt = new Trabajador(1, "ab", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(2, "ac", Departamento.MANTENIMIENTO) :: lt
  lt = new Trabajador(3, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(4, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(5, "ac", Departamento.LOGISTICA) :: lt
  lt = new Trabajador(6, "ac", Departamento.LOGISTICA) :: lt
  cs.listaTrabajadores = lt

  val server = Undertow.builder
    .addHttpListener(8082, "localhost")
    .setHandler(cs.defaultHandler)
    .build
  server.start()

  val host = "http://localhost:8082"

  for(i <- 0 to 4){
    requests.post(s"$host/addSolicitud",
      data = """{"userID": [1], "id":[""" + i + """], "desc": "descripcion", "nTrabajadores" : [3]}""")
  }
  //TEST AÑADIR SOLICITUD
  test("Comprobar que la solicitud se añade a la lista"){
    val nsolicitudes = requests.get(s"$host/nSolicitudes").text()
    assert(nsolicitudes.toInt == 5)
  }

  test("Comprobar que si el trabajador no existe no se añade la solicitud"){
    requests.post(s"$host/addSolicitud",
      data = """{"userID": [-1], "id":[7], "desc": "descripcion", "nTrabajadores" : [3]}""")
    val result = requests.get(s"$host/exist/7").text()
    assert(!result.toBoolean)
  }

  test("Comprobar que si el trabajador no es del departamento LOGISTICA no se añade la solicitud"){
    requests.post(s"$host/addSolicitud",
      data = """{"userID": [0], "id":[7], "desc": "descripcion", "nTrabajadores" : [3]}""")
    val result = requests.get(s"$host/exist/7").text()
  }

  test("Comprobar que si el id existe no se añade la solicitud"){
    requests.post(s"$host/addSolicitud",
      data = """{"userID": [1], "id":[2], "desc": "descripcion", "nTrabajadores" : [3]}""")
    val result = requests.get(s"$host/nSolicitudes").text()
    assert(result.toInt == 5)
  }


  //TEST APUNTARSE A SOLICITUD
  /*    test("Comprobar que el trabajador se añade a la lista de trabajadores de la solicitud y el número de personas disminuye en 1."){
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
      }*/
}