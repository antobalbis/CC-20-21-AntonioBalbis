package eWarehouse

class ControladorMaquinas(){
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def addMaquina(userID: Int, id : Int, nombre : String) = {
		if(!listaMaquinas.exists(m => m.ID == id)) {
			if (listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(Departamento.LOGISTICA))) {
				val maquina: Maquina = new Maquina(id, nombre)
				listaMaquinas = maquina :: listaMaquinas
			}
		}
	}

	def deleteMaquina(userID : Int, id : Int) = {
		if(listaMaquinas.exists(m => m.ID == id)) {
			if (listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(Departamento.LOGISTICA))) {
				val index: Int = listaMaquinas.indexWhere(m => m.ID == id)
				listaMaquinas = listaMaquinas.take(index) ++ listaMaquinas.drop(index + 1)
			}
		}
	}

	def getListaMaquinas(userID: Int) : List[Maquina] = {
		var result : List[Maquina] = List()
		if(listaTrabajadores.exists(t => t.ID == userID))
		for(maquina <- listaMaquinas){
			if(maquina.estado.equals(EstadoMaquina.FUNCIONANDO)) result = maquina :: result
		}
		result
	}

	def usarMaquina(id : Int, userId : Int) : Boolean = {
		var result : Boolean = false

		if(listaMaquinas.exists(m => m.ID == id) && listaTrabajadores.exists(t => t.ID == userId)){
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			if(listaMaquinas(index).estado.equals(EstadoMaquina.FUNCIONANDO) &&
			!listaMaquinas(index).isBeingUsed){
				listaMaquinas(index).isBeingUsed = true
				listaMaquinas(index).userID = userId
				result = true
			}
		}
		result
	}

	def dejarMaquina(id : Int, userID : Int) : Boolean = {
		var result : Boolean = false
		if(listaMaquinas.exists(m => m.ID == id)){
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			if(listaMaquinas(index).isBeingUsed && listaMaquinas(index).userID == userID){
				listaMaquinas(index).isBeingUsed = false
				listaMaquinas(index).userID = -1
				result = true
			}
		}
		result
	}

	def cambiarEstadoMaquina(userID : Int, id : Int, estadoMaquina: EstadoMaquina.Value): Unit ={
		if(listaTrabajadores.exists(t => t.ID == userID)) {
			val user = listaTrabajadores.indexWhere(t => t.ID == userID)
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			if (listaTrabajadores(user).departamento.equals(Departamento.MANTENIMIENTO)) {
				listaMaquinas(index).estado = estadoMaquina;
			}
		}
	}

	def averiaMaquina(userID : Int, id : Int): Unit ={
		if(listaTrabajadores.exists(t => t.ID == userID)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			if (listaMaquinas(index).estado.equals(EstadoMaquina.FUNCIONANDO)) {
				listaMaquinas(index).estado = EstadoMaquina.PENDIENTE
			}
		}
	}
}
