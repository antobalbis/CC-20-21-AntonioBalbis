package eWarehouse

class ControladorMaquinas(){
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def addMaquina(id : Int, nombre : String){
		val maquina : Maquina = new Maquina(id, nombre)
		listaMaquinas = maquina :: listaMaquinas
	}

	def deleteMaquina(id : Int){
		val index : Int = getMaquina(id)
		listaMaquinas = listaMaquinas.take(index) ++ listaMaquinas.drop(index + 1)
	}

	def getMaquina(id : Int) : Int = {
		var index : Int = -1
		for(i <- 0 to listaMaquinas.length){
			if(listaMaquinas(i).ID == id) index = i
		}
		index
	}

	def getListaMaquinas() : List[Maquina] = {
		val result : List[Maquina] = List()
		for(maquina <- listaMaquinas){
			if(maquina.estado.equals(EstadoMaquina.FUNCIONANDO) && !maquina.isBeingUsed)maquina :: result
		}
		result
	}

	def usarMaquina(id : Int) : Boolean = false

}
