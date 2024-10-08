package boletin3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UD1_B3_T2_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File file = new File(System.getProperty("user.home") + "\\AD\\refranes.txt");
        File write;

        System.out.println(
                "Dime una volcal para sustituir las del refran por la que indiques ( a, e, i, o, u, á, é, í, ó, ú, ü )");
        char vocal = sc.nextLine().toLowerCase().charAt(0);

        write = new File(
                System.getProperty("user.home") + "\\AD\\refranes_CON_" + String.valueOf(vocal).toUpperCase() + ".txt");

        if (esVocal(vocal)) {
            copiaConVocal(file, write, vocal);

        } else {
            System.out.println("Lo siento pero solo puedes introducir vocales");
        }
        sc.close();
    }

    /**
     * Funcion con la que vamos a comprobar que una letra sea una vocal
     * 
     * @param c - letra a comprobar
     * @return - en caso de que se compla devolvera true, en caso contrario false
     */
    public static boolean esVocal(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'á' || c == 'é' || c == 'í' || c == 'ó'
                || c == 'ú' || c == 'ü';
    }

    /**
     * Funcion que copia el contenido de un archivo cambiando las vocales por la
     * pasada por parametro a la funcion
     * 
     * @param f1 - fichero del que queremos copiar los datos
     * @param f2 - fichero a donde queremos copiar los datos
     * @param c  - vocal con la que vamos a sustituir a otras
     */
    public static void copiaConVocal(File f1, File f2, char c) {
        FileReader fr = null;

        FileWriter fw = null;
        try {
            fr = new FileReader(f1);
            fw = new FileWriter(f2);

            int currentChar;
            while ((currentChar = fr.read()) != -1) {
                char aux = (char) currentChar;
                if (esVocal(aux)) {
                    fw.write(c);
                } else {
                    fw.write(aux);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
