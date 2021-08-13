package eWarehouse

import EstadoMaquina._

class Maquina(id : Integer, name : String){
	val ID: Integer = id
	val nombre: String = name
	var isBeingUsed: Boolean = false
	var estado : EstadoMaquina = FUNCIONANDO

	def obtenerEstado() : EstadoMaquina = estado

	def cambiarEstado(status: EstadoMaquina){
		estado = status
	}

	def getIsBeingUsed() : Boolean = getIsBeingUsed
	def startUsing(){
		isBeingUsed = false
	}

	def stopUsing(){
		isBeingUsed = true
	}
}
