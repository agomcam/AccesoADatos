package boletin1;

import java.io.File;
import java.io.IOException;

public class UD1_B1_T4_Gomez_Camarena_Antonio {
	public static void main(String[] args) {
		File file = new File("NUEVODIR");

		if (file.mkdir()) {
			System.out.println("Directorio creado exitosamente");
		} else {
			System.out.println("El directorio no se ha podido crear");
		}
		File fichero1 = new File(file.getPath(), "Fiero1.txt");
		File fichero2 = new File(file.getPath(), "Fichero 2.txt");

		try {
			if (fichero1.createNewFile()) {
				System.out.println("Fichero 1 creado exitosamente");
			}

			if (fichero2.createNewFile()) {
				System.out.println("Fichero 2 creado exitosamente");
			}

			System.out.println("Cambiamos de nombre el fichero 1");
			System.out.println(fichero1.getName());
			fichero1.renameTo(new File(file.getPath(), "Fichero 1.txt"));
			System.out.println("nombre cambiado a " + "Fichero 1.txt");

			System.out.println("Borramos el fichero 2: " + fichero2.delete());

		} catch (IOException e) {
			System.out.println("Error al poder crear el fichero");
		}

//		Respuesta a la pregunta de donde se ha creado la carpeta al ejecutarlo desde el ide
//		La carpeta se ha creado en el raiz de nuestro proyecto de java
	}
}
