import EstadoMaquina._

class Maquina(ID: String, nombre:String){
	val ID: String = ID
	val nombre: String = nombre
	var isBeingUsed: Boolean = false
	var estado = FUNCIONANDO
	
	def getNombre():String = nombre
	def getID():String = ID
	def isInUse():Boolean = isBeingUsed
	
	def startUsing() {
		if(!isBeingUsed) isBeingUsed = true
	}
	
	def stopUsing(){
		isBeingUsed = false
	}
	
	def obtenerEstado():String = " "
	def cambiarEstado(status: EstadoMaquina){}
}
