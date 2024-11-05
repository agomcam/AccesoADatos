package pruebaExamen;

import java.io.Serializable;

//0.	(0.5 pts) Las clases Reserva y Fecha están incompletas.
//Añade lo necesario para que puedan ser almacenadas en archivos binarios.
public class Fecha implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dia;
	private int mes;
	private int anyo;
	
	public Fecha(int dia, int mes, int anyo) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAnyo() {
		return anyo;
	}


	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	@Override
	public String toString() {
		return "Fecha [dia=" + dia + ", mes=" + mes + ", anyo=" + anyo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass()) return false;
		Fecha aux = (Fecha) obj;
		if(this.getDia() != aux.getDia()) return false;
		if(this.getMes() != aux.getMes()) return false;
		if(this.getMes() != aux.getAnyo()) return false;
		return true;
	}
	
	
}
