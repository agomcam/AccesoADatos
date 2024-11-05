package boletin1;

import java.io.File;

public class UD1_B1_T3_Gomez_Camarena_Antonio {
	public static void main(String[] args) {
		String rutaPrincipal = "";
		String rutaSecundaria = System.getProperty("user.home") + "\\AD\\Archivo 1.txt";
		File fichero;

//		Comprobamo si tenemos argumentos cargados o no
		if (args.length >= 1) {
			rutaPrincipal = args[0];
		} else {
			rutaPrincipal = rutaSecundaria;
		}
		fichero = new File(rutaPrincipal);

		if (!fichero.exists()) {
			System.out.println("Lo siento pero no existe la ruta que nos has pasado");
			return;
		}
		if (fichero.isDirectory()) {
			System.out.println("Lo siento pero no es un archivo lo que nos has pasado");
			return;
		}
		System.out.println("Nombre del fichero: " + fichero.getName());
		System.out.println("Ruta: " + fichero.getPath());
		System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
		System.out.println("Es un fichero: " + fichero.isFile() + ", es un directorio: " + fichero.isDirectory());
		System.out.println("¿Se puede leer?: " + fichero.canRead() + ", ¿se puede escribir?: " + fichero.canWrite());
		System.out.println("Mi tamaño es de: " + fichero.length());
		System.out.println("El nombre de mi directorio padre es: " + new File(fichero.getParent()).getName());
	}
}
