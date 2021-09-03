package eWarehouse

class ControladorMaquinas() extends cask.MainRoutes{
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()


	def existTrabajador(userID : Int) : Boolean = {
		listaTrabajadores.exists(t => t.ID ==userID)
	}

	def checkDepartment(userID : Int, esperado : Departamento.Value) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(esperado))
	}

	@cask.postJson("/getMaquina")
	def existMaquina(mID : Seq[Int]) : Boolean = {
		listaMaquinas.exists(m => m.ID == mID(0))
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

	@cask.get("/getNMaquinas")
	def getNMaquinas(): String ={
		listaMaquinas.length.toString
	}

	@cask.postJson("/addMaquina")
	def addMaquina(userID_ : Seq[Int], id_ : Seq[Int], nombre : String) = {
		val userID = userID_(0)
		val id = id_(0)
		if(!existMaquina(id)) {
			if (existTrabajador(userID)) {
				val maquina: Maquina = new Maquina(id, nombre)
				listaMaquinas = maquina :: listaMaquinas
			}
		}
		"OK"
	}

	@cask.postJson("/rmMaquina")
	def deleteMaquina(userID : Seq[Int], id : Seq[Int]) = {
		if(existMaquina(id(0))) {
			if (checkDepartment(userID(0), Departamento.LOGISTICA)) {
				val index: Int = listaMaquinas.indexWhere(m => m.ID == id(0))
				listaMaquinas = listaMaquinas.take(index) ++ listaMaquinas.drop(index + 1)
			}
		}
	}

	@cask.postJson("/getListaMaquinas")
	def getListaMaquinas(userID: Seq[Int]) : List[Maquina] = {
		var result : List[Maquina] = List()
		if(existTrabajador(userID(0))) {
			for (maquina <- listaMaquinas) {
				if (maquina.estado.equals(EstadoMaquina.FUNCIONANDO)) result = maquina :: result
			}
		}
		result
	}

	@cask.postJson("/usarMaquina")
	def usarMaquina(id : Seq[Int], userId : Seq[Int]) : Boolean = {
		var result : Boolean = false

		if(existMaquina(id) && existTrabajador(userId(0))){
			val index = listaMaquinas.indexWhere(m => m.ID == id(0))
			if(listaMaquinas(index).estado.equals(EstadoMaquina.FUNCIONANDO) &&
			!listaMaquinas(index).isBeingUsed){
				listaMaquinas(index).isBeingUsed = true
				listaMaquinas(index).userID = userId(0)
				result = true
			}
		}
		result
	}

	@cask.postJson("/dejarMaquina")
	def dejarMaquina(id : Seq[Int], userID : Seq[Int]) : Boolean = {
		var result : Boolean = false
		if(existMaquina(id(0))){
			val index = listaMaquinas.indexWhere(m => m.ID == id(0))
			if(listaMaquinas(index).isBeingUsed && listaMaquinas(index).userID == userID(0)){
				listaMaquinas(index).isBeingUsed = false
				listaMaquinas(index).userID = -1
				result = true
			}
		}
		result
	}

	@cask.postJson("/cambiarEstado")
	def cambiarEstadoMaquina(userID : Seq[Int], id : Seq[Int], estadoMaquina_ : String): Unit ={
		val estadoMaquina = EstadoMaquina.values.find(_.toString == estadoMaquina_).get
		if(checkDepartment(userID(0), Departamento.MANTENIMIENTO) && existMaquina(id(0))) {
			val index = listaMaquinas.indexWhere(m => m.ID == id(0))
			listaMaquinas(index).estado = estadoMaquina
		}
	}

	 @cask.postJson("/cambiarEstado")
	def averiaMaquina(userID : Seq[Int], id : Seq[Int]): Unit ={
		if(existTrabajador(userID(0)) && checkMaquinaEstado(id(0), EstadoMaquina.FUNCIONANDO)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			listaMaquinas(index).estado = EstadoMaquina.PENDIENTE
		}
	}

	initialize()
}
