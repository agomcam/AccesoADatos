package boletin2;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UD1_B2_T3_Gomez_Camarena_Antonio {

    public static void main(String[] args) {

        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "datosMatriz.dat";

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

        escribirMatriz(rutaArchivo, nombreArchivo, pedirDatosMatriz());
        double matriz[][] = leerMatriz(rutaArchivo, nombreArchivo);
        mostrarMatriz(matriz);
    }

    private static void mostrarMatriz(double[][] matriz) {
        System.out.println("Mostramos la matriz");

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println("matriz [" + i + "][" + j + "] = " + matriz[i][j]);
            }
        }
    }

    /**
     * Funcion de java que pide los datos para rellenar una matriz
     * 
     * @return - double[][]
     */
    private static double[][] pedirDatosMatriz() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime cuantas filas quieres que tanga tu matriz: ");

        int filas = sc.nextInt();

        System.out.println("Dime cuantas columnas quieres que tanga tu matriz: ");

        int columnas = sc.nextInt();
        double matriz[][] = new double[filas][columnas];
        try {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.println("Dime el valor [" + i + "][" + j + "]");
                    matriz[i][j] = sc.nextDouble();
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        sc.close();
        return matriz;
    }

    /**
     * Funcion de java que guarda los datos de una matriz en un archivo binario
     * 
     * @param ruta          - del archivo
     * @param nombreArchivo - nombre del archivo
     * @param matriz        - matriz de donde vamos a obtener los datos
     */
    private static void escribirMatriz(String ruta, String nombreArchivo, double[][] matriz) {
        File file = new File(ruta, nombreArchivo);
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(file));
            System.out.println("Tamaño de matriz");
            dos.writeInt(matriz.length);
            dos.writeInt(matriz[0].length);

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    dos.writeDouble(matriz[i][j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static double[][] leerMatriz(String ruta, String nombreArchivo) {
        File file = new File(ruta, nombreArchivo);
        double matriz[][] = null;
        RandomAccessFile raf = null;
        try {

            raf = new RandomAccessFile(file, "r");

            int fila = raf.readInt();
            int columnas = raf.readInt();

            System.out.println("Fila: " + fila);
            System.out.println("Columnas:" + columnas);

            matriz = new double[fila][columnas];

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = raf.readDouble();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {

                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return matriz;
    }
}
