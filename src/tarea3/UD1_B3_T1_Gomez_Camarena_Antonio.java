package tarea3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UD1_B3_T1_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        File file;
        if (args.length > 0) {
            file = new File(args[0]);
        } else {
            file = new File(System.getProperty("user.home") + "\\AD\\refranes.txt");
        }
        System.out.println("------------leerFileReader----------------");
        leerFileReader(file);
        System.out.println();
        System.out.println();
        System.out.println("------------leerBufferedReader------------");
        leerBufferedReader(file);
    }

    /**
     * Funcion que lee un archivo a travez del metodo fileReader
     * 
     * @param f - fichero que vamos a leer
     */
    private static void leerFileReader(File f) {
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion que lee un archivo a travez del metodo BufferedReader
     * 
     * @param f - fichero que vamos a leer
     */
    private static void leerBufferedReader(File f) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));

            String i;
            while ((i = br.readLine()) != null) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
