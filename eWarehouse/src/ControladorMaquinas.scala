package eWarehouse

import cask.Request

class ControladorMaquinas() extends cask.MainRoutes{
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()


	def existTrabajador(userID : Int) : Boolean = {
		listaTrabajadores.exists(t => t.ID ==userID)
	}

	def checkDepartment(userID : Int, esperado : Departamento.Value) : Boolean = {
		listaTrabajadores.exists(t => t.ID == userID && t.departamento.equals(esperado))
	}

	@cask.get("/existMaquina/:mID")
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

	@cask.get("/getMaquina/:id")
	def getMaquina(id : Seq[Int]) : ujson.Obj = {
		val maquina = listaMaquinas(listaMaquinas.indexWhere(m => m.ID == id(0)))
		ujson.Obj("userID" -> maquina.userID.toString, "ID" -> maquina.ID.toString, "nombre" ->maquina.nombre, "uso" -> maquina.isBeingUsed, "estado" -> maquina.estado.toString)
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

	@cask.get("/getListaMaquinas/:userID")
	def getListaMaquinas(userID: Int) : ujson.Arr = {
		var result = ujson.Arr()
		if(existTrabajador(userID)) {
			for (maquina <- listaMaquinas) {
				if (maquina.estado.equals(EstadoMaquina.FUNCIONANDO)){
					val line = ujson.Obj("ID" -> maquina.ID.toString, "nombre" ->maquina.nombre, "uso" -> maquina.isBeingUsed, "estado" -> maquina.estado.toString)
					result.arr += line
				}
			}
		}
		result
	}

	@cask.put("/usarMaquina")
	def usarMaquina(request: cask.Request) : Unit = {
		val json = ujson.read(request)
		val id = json.apply("id").toString().toInt
		val userId = json.apply("userId").toString().toInt
		var result : Boolean = false

		if(existMaquina(id) && checkDepartment(userId, Departamento.LOGISTICA)){
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

	@cask.put("/dejarMaquina")
	def dejarMaquina(request : cask.Request) : Unit = {
		val json = ujson.read(request)
		val id : Int = json.apply("id").toString().toInt
		val userID : Int = json.apply("userID").toString().toInt

		if(existMaquina(id)){
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			if(listaMaquinas(index).isBeingUsed && listaMaquinas(index).userID == userID){
				listaMaquinas(index).isBeingUsed = false
				listaMaquinas(index).userID = -1
			}
		}
	}

	@cask.put("/cambiarEstado")
	def cambiarEstadoMaquina(request: cask.Request): Unit ={
		val json = ujson.read(request)
		val id : Int = json.apply("id").toString().toInt
		val userID : Int = json.apply("userID").toString().toInt
		val estadoMaquina = EstadoMaquina.values.find(_.toString.equals(json.apply("estadoMaquina_").str)).get

		if(checkDepartment(userID, Departamento.MANTENIMIENTO) && existMaquina(id)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			listaMaquinas(index).estado = estadoMaquina
		}
	}

	@cask.put("/averiar")
	def averiaMaquina(request: cask.Request): Unit ={
		val json = ujson.read(request)
		val id : Int = json.apply("id").toString().toInt
		val userID : Int = json.apply("userID").toString().toInt

		if(existTrabajador(userID) && checkMaquinaEstado(id, EstadoMaquina.FUNCIONANDO)) {
			val index = listaMaquinas.indexWhere(m => m.ID == id)
			listaMaquinas(index).estado = EstadoMaquina.PENDIENTE
		}
	}

	initialize()
}
