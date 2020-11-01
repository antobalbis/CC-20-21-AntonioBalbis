class Localidad (name : String, id : Int){
  val nombre : String = name
  val estacion_id : Int = id
  var temperatura: Double = 0
  var viento: Double = 0
  var precipitaciones: Double = 0
  var humedad: Double = 0

  def getNombre(): String = nombre
  def getID(): Int = estacion_id
  def getTemperatura(): Double = temperatura
  def getViento(): Double = viento
  def getPrecipitaciones(): Double = precipitaciones
  def getHumedad(): Double = humedad
  
  def setTemperatura(temperatura: Double){
  	this.temperatura = temperatura
  }
  
  def setViento(viento: Double){
  	this.viento = viento
  }
  
  def setPrecipitaciones(precipitaciones: Double){
  	this.precipitaciones = precipitaciones
  }
  
  def setHumedad(humedad: Double){
  	this.humedad = humedad
  }
}

