package tarea1;

import java.io.File;

public class UD1_B1_T5_Gomez_Camarena_Antonio {
	public static void main(String[] args) {
		File dr = new File("NUEVODIR");

		if (!dr.exists()) {
			System.out.println("Lo siento pero el directorio actual no existe");
			return;
		}
		
		if (dr.delete()) {
			System.out.println("Procedemos a borrar el directorio: ");
			System.out.println("Se ha podido borrar correctamente el directorio");
		} else {
			System.out.println("No se puede borrar la carpeta, ya que tiene contenido en su interior");
			System.out.println("Contiene: " + dr.list().length + " elementos");
		}

	}
}
