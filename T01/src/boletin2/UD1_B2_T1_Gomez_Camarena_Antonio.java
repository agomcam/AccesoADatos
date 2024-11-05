package boletin2;

import java.io.File;
import java.nio.file.Files;

public class UD1_B2_T1_Gomez_Camarena_Antonio {
	public static void main(String[] args) {
		int cont = 1;
		int numCopiado = 10;
		String nombre = "EstoDefinitivamenteNoEsUnVirus";

		try {
			File original = new File("src/tarea2/EstoDefinitivamenteNoEsUnVirus.jar");
			if (original.exists()) {
				while (0 < numCopiado) {
					File a = new File(original.getAbsolutePath().split(nombre)[0], nombre + "_COPIA" + cont + ".jar");
					if (!a.exists()) {
						Files.copy(original.toPath(), a.toPath());
						numCopiado--;
					}

					cont++;
				}
			}else {
				System.out.println("Lo siento no existe el archivo");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
