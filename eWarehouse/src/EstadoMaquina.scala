package eWarehouse

object EstadoMaquina extends Enumeration{
	type EstadoMaquina = String
	val FUNCIONANDO = "FUN"
	val PENDIENTE = "PEN"
	val REPARACION = "REP"
}
