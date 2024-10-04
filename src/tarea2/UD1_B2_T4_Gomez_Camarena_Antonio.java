package tarea2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UD1_B2_T4_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "datosFibonacci.dat";

        String res = "1";
        // Comprobamos de que existe el archivo
        if (!new File(rutaArchivo, nombreArchivo).exists()) {
            try {
                // En caso de que el archivo no exista lo creamos
                new File(rutaArchivo, nombreArchivo).createNewFile();
                System.out.println("Hemos tenido que crear el archivo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while (!res.equalsIgnoreCase("3")) {
            System.out.println("Actualmente hay " + (new File(rutaArchivo, nombreArchivo).length() / Integer.BYTES)
                    + " números guardados");
            System.out.println("1 Escribir 10 números. \n2 Mostrar con salto X. \n3 Salir.");

            res = sc.nextLine();

            switch (res) {
                case "1":
                    escribir10();
                    break;
                case "2":
                    try {
                        System.out.println("Con cuantos saltos quieres ver el resultado");
                        int saltos = sc.nextInt();
                        sc.nextLine();
                        mostrarConSalto(saltos);
                    } catch (InputMismatchException e) {
                        e.printStackTrace();
                    }

                    break;
                case "3":
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Lo siento pero no tenemos esta respuesta");
                    break;
            }

        }
        sc.close();
    }

    /**
     * Funcion que escribe los 10 siguientes números de la sucesión fibonacci
     */
    private static void escribir10() {
        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "datosFibonacci.dat";

        File file = new File(rutaArchivo, nombreArchivo);

        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(file, "rw");
            if (file.length() == 0) {
                // Escribimos el 0 y el 1
                raf.writeInt(0);
                raf.writeInt(1);
                for (int i = 0; i < 8; i++) {
                    raf.seek(raf.length() - Integer.BYTES * 2);
                    raf.writeInt(raf.readInt() + raf.readInt());
                }
            } else {
                raf.seek(raf.length());
                for (int i = 0; i < 10; i++) {
                    raf.seek(raf.length() - Integer.BYTES * 2);
                    raf.writeInt(raf.readInt() + raf.readInt());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion que te muestra los datos de un fichero en el que tenemos guardadas la
     * sucesión fibonacci con los salto que indique el usuario
     * 
     * @param salto - saltos
     */
    private static void mostrarConSalto(int salto) {
        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "datosFibonacci.dat";

        File file = new File(rutaArchivo, nombreArchivo);

        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(file, "rw");
            while (true) {
                System.out.print(raf.readInt() + " | ");
                if (salto != 0) {
                    raf.seek(raf.getFilePointer() + Integer.BYTES * salto);
                }
            }

        } catch (IOException e) {
            System.out.println();
            System.out.println("Lectura echa correctamente");
        } finally {
            try {

                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
