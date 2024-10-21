package pruebaExamen;

import java.io.Serializable;

//0.	(0.5 pts) Las clases Reserva y Fecha están incompletas.
//Añade lo necesario para que puedan ser almacenadas en archivos binarios.
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cliente;
	private int numHabitacion;
	private Fecha fechaInicio;
	private Fecha fechaFin;

	public Reserva(String cliente, int numHabitacion, Fecha fechaInicio, Fecha fechaFin) {
		super();
		this.cliente = cliente;
		this.numHabitacion = numHabitacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}


	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Fecha fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Fecha getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Fecha fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public String toString() {
		return "Reserva [cliente=" + cliente + ", numHabitacion=" + numHabitacion + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass()) return false;
		Reserva aux = (Reserva) obj;
		if(!this.getCliente().equals(aux.getCliente()))	return false;
		if(this.getNumHabitacion() != aux.getNumHabitacion()) return false;
		if(!this.getFechaInicio().equals(aux.getFechaInicio())) return false;
		if(!this.getFechaFin().equals(aux.getFechaFin())) return false;
		return true;
	}
}
