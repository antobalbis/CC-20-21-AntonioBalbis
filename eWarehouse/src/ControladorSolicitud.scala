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
			listaSolicitudes = new Solicitud(id, userID, desc, nTrabajadores) :: listaSolicitudes
		}
	}

	def apuntarseSolicitud(userID : Int, id : Int): Unit ={
		if(checkDepartment(userID, Departamento.LOGISTICA) && existSolicitud(id)){
			if(!(getSolicitudByID(id).userID == userID) && !getSolicitudByID(id).trabajadores.exists(t => t == userID)
				&& getSolicitudByID(id).restantes != 0){
				getSolicitudByID(id).trabajadores = userID :: getSolicitudByID(id).trabajadores
				getSolicitudByID(id).restantes = getSolicitudByID(id).restantes-1
			}
		}
	}

	def removeSolicitud(id: String) = {}
}
