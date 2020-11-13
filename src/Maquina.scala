import EstadoMaquina._

class Maquina(id: String, name:String){
	val ID: String = ID
	val nombre: String = nombre
	var isBeingUsed: Boolean = false
	var estado = FUNCIONANDO
	
	def obtenerEstado():String = " "
	def cambiarEstado(status: EstadoMaquina){}
}
