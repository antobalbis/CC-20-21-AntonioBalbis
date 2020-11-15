import Departamento._

class Trabajador (name: String, depart: Departamento){
	val nombre: String = name
	val departamento = depart

	def crearSolicitud(descripcion: String){}
	def addMaquina(nombre: String){}
	def eliminarMaquina(id: String){}
	def estaDisponible(id: String): Boolean = false
	
}
