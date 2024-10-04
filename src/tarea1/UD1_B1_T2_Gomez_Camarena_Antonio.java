package tarea1;

import java.io.File;

public class UD1_B1_T2_Gomez_Camarena_Antonio {
	public static void main(String[] args) {
		String rutaPrincipal = "";
		String rutaSecundaria = System.getProperty("user.home") + "\\AD";
		File directorio;
		
//		Comprobamo si tenemos argumentos cargados o no
		if (args.length >= 1) {
			rutaPrincipal = args[0];
		} else {
			rutaPrincipal = rutaSecundaria;
		}
		directorio = new File(rutaPrincipal);
		
		if (!directorio.exists()) {
			System.out.println("Lo siento pero no existe la ruta que nos has pasado");
			return;
		}
		if (!directorio.isDirectory()) {
			System.out.println("Lo siento pero no es un directorio lo que nos has pasado");
			return;
		}
		
//		Obtenemos todos los nombres de los archivos y directorios, de este modo
//		Podremos crear nuevos archivos file para comprobar el tipo y saber que son
		
		for (String nombres : directorio.list()) {
			File arch = new File(directorio.getPath(), nombres);

			if (arch.isDirectory()) {
				System.out.println(nombres + " - Directorio");
			}else {
				System.out.println(nombres + " - Archivo");
			}
			
		}

	}
}
