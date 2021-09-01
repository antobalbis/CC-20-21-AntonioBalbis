package eWarehouse

class Solicitud(id: Int, nombre: String, desc: String, nTrabajadores: Int){
	val ID: Int = id
	val nombre_solicitante: String = nombre
	val trabajadores : List[Trabajador] = List()
	val nPersonas: Int = 0
	var descripción: String = desc
	var restantes: Int = nPersonas
}
