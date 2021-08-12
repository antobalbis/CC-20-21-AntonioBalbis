package eWarehouse

class ControladorMaquinas(){
	val listaMaquinas : List[Maquina] = List()
	val listaTrabajadores : List[Trabajador] = List()

	def addMaquina(maquina : Maquina, tipo){
		if(!maquinaExist(maquina.ID)) maquina +: listaMaquinas
	}

	def deleteMaquina(maquina : Maquina){
		if(maquinaExist(maquina.ID)) maquina -: listaMaquinas
	}

	def removeMaquina (id : String){}

	def maquinaExist(id : Int) : Boolean = {
		var exist : Boolean = false
		for(maquina <- listaMaquinas){
			if(maquina.ID == id) exist = true
		}
		exist
	}

	def getListaMaquinas() : List[Maquina] = {
		val result : List[Maquina] = List()
		for(maquina <- listaMaquinas){
			if(maquina.estado == "FUN") result +: maquina
		}
		result
	}

}
