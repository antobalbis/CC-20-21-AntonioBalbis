import Departamento._

class Trabajador (name: String, depart: Departamento){
	val nombre: String = name
	val departamento = depart
	
	def getNombre():String = nombre
	def getDepartamento():Departamento = departamento
	
	def estaDisponible(id_maquina: String) : Boolean = false
	def getMaquinasDisponibles(nombre: String) : Int = 0
	def getMaquinasAveriadas(){}
}
