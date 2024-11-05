package boletin1;

import java.io.File;

public class UD1_B1_T6_Gomez_Camarena_Antonio {

	public static void main(String[] args) {
		String rutaPrincipal = "";
		String rutaSecundaria = System.getProperty("user.home") + "\\AD";
		File file;

		String separacionActual = "";

//		Comprobamo si tenemos argumentos cargados o no
		if (args.length >= 1) {
			rutaPrincipal = args[0];
		} else {
			rutaPrincipal = rutaSecundaria;
		}
		file = new File(rutaPrincipal);
		if (!file.exists()) {
			System.out.println("Lo siento pero no existe la ruta que nos has pasado");
			return;
		}
		if (!file.isDirectory()) {
			System.out.println("Lo siento pero no es un directorio lo que nos has pasado");
			return;
		}

//		Llamamos a la función para mostrar las carpetas
		mostrarContenidoTotal(file, separacionActual);
	}

	/**
	 * Función que al pasarle un directorio te muestra todo su contenido
	 * 
	 * @param file - Directorio el cual queremos ver todo su contenido
	 * @param sep  - Espacio del cual partimos
	 */
	public static void mostrarContenidoTotal(File file, String sep) {
		sep += "-> ";
		String interior[] = file.list();
		for (String nombre : interior) {
//			Creamos un nuevo archivo para poder trabajar con este
			File ficheroNuevo = new File(file.getPath(), nombre);
			
			System.out.println(sep + ficheroNuevo.getName());
			
//			Comprobamos de que si es un directorio, en caso de que lo sea llamamos de nuevo a nuestra función
			if (ficheroNuevo.isDirectory()) {
				mostrarContenidoTotal(ficheroNuevo, sep);
			}
		}
	}
}
