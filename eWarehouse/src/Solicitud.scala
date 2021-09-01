package eWarehouse

class Solicitud(id: Int, userid: Int, desc: String, nTrabajadores: Int){
	val ID: Int = id
	val userID: Int = userid
	var trabajadores : List[Int] = List()
	var descripción: String = desc
	var restantes: Int = nTrabajadores
}
