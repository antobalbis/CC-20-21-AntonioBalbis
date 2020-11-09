import Departamento._

class Trabajador (id: String, name: String, depart: Departamento){
	val ID: String = id
	val nombre: String = name
	val departamento = depart
	
	def estaDisponible(id_maquina: String) : Boolean = false
	def getMaquinasDisponibles(nombre: String) : Int = 0
}
