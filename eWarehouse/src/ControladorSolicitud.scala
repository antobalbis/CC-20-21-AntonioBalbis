package eWarehouse

class ControladorSolicitud extends cask.MainRoutes{
	var listaSolicitudes: List[Solicitud] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def existTrabajador(userID : Int) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID)
	}

	def checkDepartment(userID : Int, esperado : Departamento.Value) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(esperado))
	}

	@cask.get("/exist/:id")
	def existSolicitud(id : Int) : Boolean = {
		listaSolicitudes.exists(s => s.ID == id)
	}

	def getSolicitud(id : Int) : Solicitud = {
		listaSolicitudes(listaSolicitudes.indexWhere(s => s.ID == id))
	}

	@cask.get("/nSolicitudes")
	def getNSolicitudes(): Int ={
		listaSolicitudes.size
	}

	@cask.get("/getLista/:id")
	def getTrabajadores(id : Int): ujson.Arr ={
		var lista = ujson.Arr()
		for(i <- getSolicitud(id).trabajadores) {
			val line = ujson.Obj("ID" -> i)
			lista.arr += line
		}
		lista
	}

	@cask.get("/getSolicitud/:id")
	def getSolicitudByID(id : Int) : ujson.Obj = {
		val solicitud = listaSolicitudes(listaSolicitudes.indexWhere(s => s.ID == id))
		ujson.Obj("userID" -> solicitud.userID.toString, "ID" -> solicitud.ID.toString,
			"descripción" -> solicitud.descripción, "restantes" -> solicitud.restantes.toString)
	}

	@cask.postJson("/addSolicitud")
	def addSolicitud(userID : Seq[Int], id: Seq[Int], desc : String, nTrabajadores : Seq[Int]) = {
		if (checkDepartment(userID(0), Departamento.LOGISTICA) && !existSolicitud(id(0))) {
			listaSolicitudes = new Solicitud(id(0), userID(0), desc, nTrabajadores(0)) :: listaSolicitudes
		}
	}

	@cask.put("/apuntarse")
	def apuntarseSolicitud(request: cask.Request): Unit ={
		val json = ujson.read(request)
		val id : Int = json.apply("id").toString().toInt
		val userID : Int = json.apply("userID").toString().toInt

		if(checkDepartment(userID, Departamento.LOGISTICA) && existSolicitud(id)){
			var solicitud = getSolicitud(id)

			if(solicitud.userID != userID && solicitud.restantes != 0 &&
				!solicitud.trabajadores.exists(t => t == userID)){

				solicitud.trabajadores = userID :: solicitud.trabajadores
				solicitud.restantes = solicitud.restantes-1
			}
		}
	}

	def removeSolicitud(id: String) = {}

	initialize()
}