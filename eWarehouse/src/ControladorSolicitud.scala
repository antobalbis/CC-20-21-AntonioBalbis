package eWarehouse

class ControladorSolicitud{
	var listaSolicitudes: List[Solicitud] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def existTrabajador(userID : Int) : Boolean = {
		listaTrabajadores.exists(t => t.ID ==userID)
	}

	def checkDepartment(userID : Int, esperado : Departamento.Value) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(esperado))
	}

	def existSolicitud(id : Int) : Boolean = {
		listaSolicitudes.exists(s => s.ID == id)
	}

	def getSolicitudByID(id : Int) : Solicitud = {
		listaSolicitudes(listaSolicitudes.indexWhere(s => s.ID == id))
	}

	def addSolicitud(userID : Int, id: Int, desc : String, nTrabajadores : Int) = {
		if (checkDepartment(userID, Departamento.LOGISTICA) && !existSolicitud(id)) {
			listaSolicitudes = new Solicitud(id, userID, desc,nTrabajadores) :: listaSolicitudes
		}
	}

	def apuntarseSolicitud(userID : Int, id : Int): Unit ={

	}

	def removeSolicitud(id: String) = {}
}
