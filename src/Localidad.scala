class Localidad (name : String, id : Int){
  val nombre : String = name
  val estacion_id : Int = id
  var temperatura: Double = 0
  var viento: Double = 0
  var precipitaciones: Double = 0
  var humedad: Double = 0

  def getNombre(): String = ""
  def getID(): Int = 0
  def getTemperatura(): Double = ""
  def getViento(): Double = 0.0
  def getPrecipitaciones(): Double = 0.0
  def getHumedad(): Double = 0.0
}

