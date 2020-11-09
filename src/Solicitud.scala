class Solicitud(id: String, nombre: String, desc: String, int npersonas){
	val ID: String = id
	val nombre_solicitante: String = nombre
	val nPersonas: Int = nPersonas
	var descripción: String = desc
	var restantes:Int = nPersonas
	
	def getID():String = ID
	def getNombreSolicitante():String = nombre_solicitante
	def recibirSolicitud(){}
}
