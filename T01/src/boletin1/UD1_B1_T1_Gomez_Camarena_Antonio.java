/**
 * 
 */
package boletin1;

/**
 * 
 */
public class UD1_B1_T1_Gomez_Camarena_Antonio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("El sistema operativo es: " + System.getProperty("os.name"));
		System.out.println("La versión del SO es: " + System.getProperty("os.version"));
		System.out.println("Su tipo de arquitectura es: " + System.getProperty("os.arch"));
		System.out.println();
		System.out.println("El usuario actual es: " + System.getProperty("user.name"));
		System.out.println("El directorio HOME es: " + System.getProperty("user.home"));
		System.out.println("El directorio donde me ejecuto es: " + System.getProperty("user.dir"));
		System.out.println();
		System.out.println("Java se encuentra instalado en: " + System.getProperty("java.home"));
		System.out.println("Su versión es: " + System.getProperty("java.version"));
		System.out.println("El nombre del vendedor es: " + System.getProperty("java.vendor"));
		System.out.println("Se puede encontrar en: " + System.getProperty("java.vendor.url"));
		System.out.println();
		System.out
				.println("Para las rutas se puede usar el carácter separador:" + System.getProperty("file.separator"));
		System.out.println(
				"Y dentro de los ficheros se usa el separador de línea: " + System.getProperty("path.separator"));
	}

}
