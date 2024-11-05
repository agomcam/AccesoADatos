package boletin2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UD1_B2_T2_Gomez_Camarena_Antonio {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "datos.dat";
        int resMenu = 1;
        String res = "s";
        while (res.equalsIgnoreCase("s")) {
            if (new File(rutaArchivo, nombreArchivo).length() > 0) {
                System.out.println("1. Leer contenido \n2. Escribir números");
                resMenu = sc.nextInt();
                sc.nextLine();
                switch (resMenu) {
                    case 1:
                        leer(rutaArchivo, nombreArchivo);
                        break;
                    case 2:
                        escribir(rutaArchivo, nombreArchivo);

                        break;
                    default:
                        System.out.println("No tenemos esta opción");
                        break;
                }

            } else {
                System.out.println("Vamos a meter números, ya que no tiene contenido en tu archivo");
                escribir(rutaArchivo, nombreArchivo);
            }
            System.out.println("¿Quieres ejecutar el programa de nuevo? (s/n)");
            res = sc.nextLine();
        }
        sc.close();
    }

    /**
     * Funcion que pasandole la ruta y el nombre de un archivo puedes escribir en el
     * 
     * @param ruta          - ruta del archivo
     * @param nombreArchivo - nombre del archivo
     */
    private static void escribir(String ruta, String nombreArchivo) {
        File file = new File(ruta, nombreArchivo);
        ArrayList<Integer> num = new ArrayList<Integer>();
        String respuesta = "s";

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            while (respuesta.equalsIgnoreCase("s")) {

                System.out.println("Dime cual es el número que quieres añadir: ");
                num.add(sc.nextInt());
                sc.nextLine();

                System.out.println("¿Quieres añadir otro número? (s/n)");
                respuesta = sc.nextLine();
            }
            for (Integer integer : num) {
                dos.writeInt(integer);
                System.out.println(integer);
            }
            dos.close();
        } catch (NumberFormatException e) {
            System.out.println("No puedes escribir letras solo se admiten números");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcion que pasandole la ruta y el nombre de un archivo te lee su contenido
     * 
     * @param ruta          - ruta del archivo
     * @param nombreArchivo - nombre del archivo
     */
    private static void leer(String ruta, String nombreArchivo) {
        File file = new File(ruta, nombreArchivo);
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(file));
            int number = 0;
            while (true) {
                number = dis.readInt();
                System.out.println("Numero: " + number);
            }
        } catch (IOException e) {
            System.out.println("Lectura del documento completada");
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar fichero");
                e.printStackTrace();
            }
        }
    }

}
