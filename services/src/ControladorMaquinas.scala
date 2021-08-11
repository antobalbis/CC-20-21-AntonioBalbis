package eWarehouse

class ControladorMaquinas(){
	val listaMaquinas : List[Maquina] = List()

	def addMaquina(maquina : Maquina, trabajador_id : String){
		if(!maquinaExist(maquina.ID)) maquina +: listaMaquinas
	}

	def removeMaquina (id : String){}

	def maquinaExist(id : Int) : Boolean = {
		var exist : Boolean = false
		for(maquina <- listaMaquinas){
			if(maquina.ID == id) exist = true
		}
		exist
	}
}
