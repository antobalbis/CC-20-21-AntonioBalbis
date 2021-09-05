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
  test("Comprobar que el trabajador se añade a la lista de trabajadores de la solicitud y el número de personas disminuye en 1."){
    val result = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes = result.apply("restantes").str.toInt

    requests.put(s"$host/apuntarse", data = """{"userID": 3, "id" : 0}""")

    val result2 = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes2 = result2.apply("restantes").str.toInt
    val userID = result2.apply("userID").str.toInt

    val lista = ujson.read(requests.get(s"$host/getLista/0"))
    var trabajador : Boolean = false
    if(lista(0)("ID").toString().toInt == 3) trabajador = true

    assert(restantes == restantes2 + 1)
    assert(trabajador)
  }

  test("Comprobar que si el trabajador no existe no se añade a la lista y el número de personas se mantiene."){
    val result = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes = result.apply("restantes").str.toInt
    requests.put(s"$host/apuntarse", data = """{"userID": 13, "id" : 0}""")
    val result2 = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes2 = result2.apply("restantes").str.toInt
    assert(restantes == restantes2)
  }

  test("Comprobar que si el trabajador ya está en la lista el número de personas restantes se mantiene."){
    val result = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes = result.apply("restantes").str.toInt

    requests.put(s"$host/apuntarse", data = """{"userID": 3, "id" : 0}""")

    val result2 = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes2 = result2.apply("restantes").str.toInt
    assert(restantes == restantes2)
  }

  test("Comprobar que si se ha alcanzado el número de trabajadores no se añada a otro trabajador."){
    requests.put(s"$host/apuntarse", data = """{"userID": 4, "id" : 0}""")
    requests.put(s"$host/apuntarse", data = """{"userID": 5, "id" : 0}""")
    //max apuntados

    val result = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes = result.apply("restantes").str.toInt

    requests.put(s"$host/apuntarse", data = """{"userID": 6, "id" : 0}""")

    val result2 = ujson.read(requests.get(s"$host/getSolicitud/0"))
    val restantes2 = result2.apply("restantes").str.toInt
    assert(restantes == restantes2)
  }

  test("Comprobar que si el trabajador no es de logística no se añade a la lista."){
    val result = ujson.read(requests.get(s"$host/getSolicitud/1"))
    val restantes = result.apply("restantes").str.toInt

    requests.put(s"$host/apuntarse", data = """{"userID": 2, "id" : 1}""")

    val result2 = ujson.read(requests.get(s"$host/getSolicitud/1"))
    val restantes2 = result2.apply("restantes").str.toInt
    assert(restantes == restantes2)
  }

  test("Comprobar que si el id es el id del solicitante no se añade a la lista."){
    val result = ujson.read(requests.get(s"$host/getSolicitud/1"))
    val restantes = result.apply("restantes").str.toInt

    requests.put(s"$host/apuntarse", data = """{"userID": 1, "id" : 1}""")

    val result2 = ujson.read(requests.get(s"$host/getSolicitud/1"))
    val restantes2 = result2.apply("restantes").str.toInt
    assert(restantes == restantes2)
  }

}