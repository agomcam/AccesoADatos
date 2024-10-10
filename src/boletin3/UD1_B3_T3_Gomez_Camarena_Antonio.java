package boletin3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UD1_B3_T3_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        File f1 = new File(System.getProperty("user.home") + "\\AD", "refranes.txt");
        File f2 = new File(System.getProperty("user.home") + "\\AD\\copias");

        copyTextFile(f1, f2, false); // Bien a la primera, falla a la segunda
        copyTextFile(f1, f2, true); // Bien
        copyTextFile(f1, f1, true); // Cuidado, Falla
        copyTextFile(f1, f1, false); // Falla
        copyTextFile(f2, f1, true); // Falla
    }

    /**
     * Funcion que comprueba que se pueda copiar un fichero correctamente en caso de
     * que se pueda llamaremos a la funcion copyTextFile
     * 
     * @param in      - fichero a copiar
     * @param out     - fichero a donde vamos a copiarlo
     * @param bandera - para saber si podemos reemplazar el fichero
     */
    public static void copyTextFile(File in, File out, boolean bandera) {
        if (!in.exists()) {
            System.out.println("Lo sieno pero el fichero no existe");
            return;
        }
        // Comprobamos de que el archivo a copiar no sea un directorios
        if (in.isDirectory()) {
            System.out.println("Lo siento pero es un directorio no puedes seguir");
            return;
        }
        // Comprobamos de que el archivo original y el destino no sea lo misma
        if (in == out) {
            System.out.println("El archivo de origen no puede ser el mismo que el de destino");
            return;
        }
        // Comprobamos de que se pueda escribir el archivo
        if (!bandera && out.exists()) {
            System.out.println("No se ha dado permiso para sobrescribir el archivo");
            return;
        }
        // Comprobamos si es un directorio o un fichero
        if (out.isDirectory()) {
            File f = new File(out, in.getName());
            System.out.println(f);
            copyTextFile(in, f);

            return;
        }
        // Comprobamos si vamos a guardar en una carpeta
        if (!out.exists()) {
            if (out.getName().split(".").length == 0) {
                if (out.mkdir()) {
                    File f = new File(out, in.getName());
                    copyTextFile(in, f);
                    return;
                }

            }
        }
        // En caso de que no se cumpla lo anterior haremos esto
        copyTextFile(in, out);

    }

    /**
     * Funcion que sirve para copiar un fichero en otro fichero
     * 
     * @param in  - fichero el cual queremos copiar
     * @param out - fichero donde queremos copiarlo
     */
    private static void copyTextFile(File in, File out) {

        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader(in);
            fw = new FileWriter(out);

            if (in.canRead() && out.canRead()) {
                int aux;
                while ((aux = fr.read()) != -1) {

                    fw.write((char) aux);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                fw.close();
                System.out.println("Archivo copiado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
