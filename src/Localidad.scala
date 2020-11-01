class Localidad (name : String, id : Int){
  val nombre : String = name
  val estacion_id : Int = id
  var viento: Double = 0
  var precipitaciones: Double = 0
  var humedad: Double = 0

  def getNombre(): String = nombre
  def getID(): Int = id

}

