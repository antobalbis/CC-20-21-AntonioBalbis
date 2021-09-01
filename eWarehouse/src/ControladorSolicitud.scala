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

	def addSolicitud(userID : Int, id: Int, desc : String, nTrabajadores : Int) = {
		if (checkDepartment(userID, Departamento.LOGISTICA) && !existSolicitud(id)) {
			val nombre : String = listaTrabajadores(listaTrabajadores.indexWhere(t => t.ID == userID)).nombre
			listaSolicitudes = new Solicitud(id, nombre, desc,nTrabajadores) :: listaSolicitudes
		}
	}

	def removeSolicitud(id: String) = {}
}
