package eWarehouse

object EstadoMaquina extends Enumeration{
  type EstadoMaquina=Value
  val FUNCIONANDO = Value("FUNCIONANDO")
  val PENDIENTE = Value("PENDIENTE")
  val REPARACION=Value("REPARACION")

}
