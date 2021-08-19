package eWarehouse

import Departamento.Departamento
import EstadoMaquina.EstadoMaquina

class Trabajador (id : Int, name: String, depart: Departamento){
	val ID : Int = id
	val nombre: String = name
	var departamento = depart
}
