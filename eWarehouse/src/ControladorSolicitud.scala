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

	def existSolicitud(id : Int) : Boolean = {
		listaSolicitudes.exists(s => s.ID == id)
	}

	@cask.get("/exist/:id")
	def existSolicitud(id : Seq[Int]) : Boolean = {
		listaSolicitudes.exists(s => s.ID == id(0))
	}

	def getSolicitud(id : Int) : Solicitud = {
		listaSolicitudes(listaSolicitudes.indexWhere(s => s.ID == id))
	}

	@cask.get("/nSolicitudes")
	def getNSolicitudes(): Int ={
		listaSolicitudes.size
	}

	@cask.get("/getSolicitud/:id")
	def getSolicitudByID(id : Seq[Int]) : ujson.Obj = {
		val solicitud = listaSolicitudes(listaSolicitudes.indexWhere(s => s.ID == id(0)))
		ujson.Obj("userID" -> solicitud.userID.toString, "ID" -> solicitud.ID.toString,
			"descripción" -> solicitud.descripción, "restantes" -> solicitud.restantes.toString)
	}

	@cask.postJson("/addSolicitud")
	def addSolicitud(userID : Seq[Int], id: Seq[Int], desc : String, nTrabajadores : Seq[Int]) = {
		if (checkDepartment(userID(0), Departamento.LOGISTICA) && !existSolicitud(id(0))) {
			listaSolicitudes = new Solicitud(id(0), userID(0), desc, nTrabajadores(0)) :: listaSolicitudes
		}
	}

	@cask.postJson("/apuntarse")
	def apuntarseSolicitud(userID : Seq[Int], id : Seq[Int]): Unit ={
		if(checkDepartment(userID(0), Departamento.LOGISTICA) && existSolicitud(id(0))){
			var solicitud = getSolicitud(id(0))

			if(!(solicitud.userID == userID(0)) && solicitud.trabajadores.exists(t => t == userID(0))
				&& solicitud.restantes != 0){
				solicitud.trabajadores = userID(0) :: solicitud.trabajadores
				solicitud.restantes = solicitud.restantes-1
			}
		}
	}

	def removeSolicitud(id: String) = {}

	initialize()
}