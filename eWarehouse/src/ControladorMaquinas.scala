package eWarehouse

class ControladorMaquinas(){
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def addMaquina(id : Int, nombre : String){
		val maquina : Maquina = new Maquina(id, nombre)
		if(getMaquina(maquina.ID) == -1) listaMaquinas = maquina :: listaMaquinas
	}

	def deleteMaquina(id : Int){
		val index : Int = getMaquina(id)
		listaMaquinas = listaMaquinas.take(index) ++ listaMaquinas.drop(index + 1)
	}

	def getMaquina(id : Int) : Int = {
		var index : Int = -1
		for (i <- 0 to listaMaquinas.length - 1) {
				if (listaMaquinas(i).ID == id) index = i
			}
		index
	}

	def getListaMaquinas() : List[Maquina] = {
		var result : List[Maquina] = List()
		for(maquina <- listaMaquinas){
			if(maquina.estado.equals(EstadoMaquina.FUNCIONANDO)) result = maquina :: result
		}
		result
	}

	def usarMaquina(id : Int, userId : Int) : Boolean = {
		var result : Boolean = false

		if(listaMaquinas.exists(m => m.ID == id)){
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

}
