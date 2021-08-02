package eWarehouse

object EstadoMaquina extends Enumeration{
	type EstadoMaquina = Value
	val FUNCIONANDO, PENDIENTE, REPARACION = Value
}
