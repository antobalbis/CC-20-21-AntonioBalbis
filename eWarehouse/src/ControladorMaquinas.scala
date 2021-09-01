package eWarehouse

class ControladorMaquinas(){
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()


	def existTrabajador(userID : Int) : Boolean = {
		listaTrabajadores.exists(t => t.ID ==userID)
	}

	def checkDepartment(userID : Int, esperado : Departamento.Value) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(esperado))
	}

	def existMaquina(mID : Int) : Boolean = {
		listaMaquinas.exists(m => m.ID == mID)
	}

	def checkMaquinaEstado(mID : Int, estado : EstadoMaquina.Value) : Boolean = {
		listaMaquinas.exists(m => m.ID == mID && m.estado.equals(estado))
	}

	def checkMaquinaInUse(mID : Int): Unit ={
		listaMaquinas(listaMaquinas.indexWhere(m => m.ID == mID)).isBeingUsed
	}

	def addMaquina(userID: Int, id : Int, nombre : String) = {
		if(!existMaquina(id)) {
			if (existTrabajador(userID)) {
				val maquina: Maquina = new Maquina(id, nombre)
				listaMaquinas = maquina :: listaMaquinas
			}
		}
	}

	def deleteMaquina(userID : Int, id : Int) = {
		if(existMaquina(id)) {
			if (checkDepartment(userID, Departamento.LOGISTICA)) {
				val index: Int = listaMaquinas.indexWhere(m => m.ID == id)
				listaMaquinas = listaMaquinas.take(index) ++ listaMaquinas.drop(index + 1)
			}
		}
	}

	def getListaMaquinas(userID: Int) : List[Maquina] = {
		var result : List[Maquina] = List()
		if(existTrabajador(userID)) {
			for (maquina <- listaMaquinas) {
				if (maquina.estado.equals(EstadoMaquina.FUNCIONANDO)) result = maquina :: result
			}
		}
		result
	}

	def usarMaquina(id : Int, userId : Int) : Boolean = {
		var result : Boolean = false

		if(existMaquina(id) && existTrabajador(userId)){
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
		if(existMaquina(id)){
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
		if(checkDepartment(userID, Departamento.MANTENIMIENTO) && existMaquina(id)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			listaMaquinas(index).estado = estadoMaquina;
		}
	}

	def averiaMaquina(userID : Int, id : Int): Unit ={
		if(existTrabajador(userID) && checkMaquinaEstado(id, EstadoMaquina.FUNCIONANDO)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			listaMaquinas(index).estado = EstadoMaquina.PENDIENTE
		}
	}

	
}
