package eWarehouse

class Solicitud(id: String, nombre: String, desc: String, npersonas: Int){
	val ID: String = id
	val nombre_solicitante: String = nombre
	val nPersonas: Int = npersonas
	var descripción: String = desc
	var restantes: Int = nPersonas

}
