package eWarehouse

class ControladorMaquinas(){
	var listaMaquinas : List[Maquina] = List()
	var listaTrabajadores : List[Trabajador] = List()

	def addMaquina(id : Int, nombre : String){
		val maquina : Maquina = new Maquina(id, nombre)
		maquina :: listaMaquinas
	}

	def deleteMaquina(id : Int){

	}

	def getMaquina(id : Int) : Int = {
		var index : Int = -1
		var i = 0
		for(i <- 0 to listaMaquinas.length){
			if(listaMaquinas(i) == id) index = i
		}
		index
	}

	def getListaMaquinas() : List[Maquina] = {
		val result : List[Maquina] = List()
		for(maquina <- listaMaquinas){
			if(maquina.estado == "FUN" && maquina.isBeingUsed == false)maquina :: result
		}
		result
	}

	def usarMaquina(id : Int) : Boolean = false

}
