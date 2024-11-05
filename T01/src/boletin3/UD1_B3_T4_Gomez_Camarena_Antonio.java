package boletin3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class UD1_B3_T4_Gomez_Camarena_Antonio {
    // Elabora un programa que permita al usuario guardar propiedades. Las
    // propiedades tienen un nombre y un valor.

    // No olvides mostrar mensajes indicando lo que ocurre.

    // Usa el archivo: “user.home\AD\misPropiedades.props”

    // Muestra el siguiente menú:

    // 1. Ver propiedades
    // 2. Introducir
    // 3. Eliminar
    // 4. Salir

    // Elabora y usa los métodos:
    // public static void MostrarPropiedades(File f);
    // Muestra las propiedades del archivo.
    // public static void IntroducirPropiedad(File f);
    // Pide por teclado un nombre y un valor, lo introduce en el archivo.
    // Si el nombre ya existe, actualiza el valor.
    // Cuidado con borrar el contenido
    // ·public static void EliminarPropiedad(File f)
    // Pide por teclado un nombre.
    // ·private static Properties cargar(File f)
    // Lee el archivo y retorna un objeto del tipo Properties.
    // Si el archivo no existe, retorna un Properties nuevo.
    // private static void guardar(Properties p, File f)
    // Guarda el objeto Propeties en el archivo.
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        File file = new File(System.getProperty("user.home") + "\\AD\\misPropiedades.props");
        int res = 1;

        try {
            while (res != 4) {
                System.out.println("1. Ver propiedades");
                System.out.println("2. Introducir");
                System.out.println("3. Eliminar");
                System.out.println("4. Salir");
                res = sc.nextInt();
                sc.nextLine();

                switch (res) {
                    case 1:
                        mostrarPropiedades(file);
                        break;
                    case 2:
                        introducirPropiedad(file);
                        break;
                    case 3:
                        eliminarPropiedad(file);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa");
                        break;
                    default:
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Solo puedes meter números");
        } finally {
            sc.close();
        }

    }

    /**
     * Funcion que sirve para introducir una propiedad
     * 
     * @param f - archivo donde se encuentran las propiedades guardadas
     */
    public static void introducirPropiedad(File f) {
        Properties config = cargar(f);
        System.out.println("Dime cual es el nombre de la propiedad que quieres añadir: ");
        String nombre = sc.nextLine();

        if (config.getProperty(nombre) != null) {
            System.out.println("Ya tenemos una propiedad con este nombre, dime cual es su nuevo valor:");
            String valorPropiedad = sc.nextLine();
            config.remove(nombre);
            config.setProperty(nombre, valorPropiedad);
        } else {
            System.out.println("Dime cual es el valor de la propiedad");
            String valorPropiedad = sc.nextLine();
            config.setProperty(nombre, valorPropiedad);
        }

        if (f.canWrite()) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(f);
                config.store(fw, "Fichero de configuración");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Funcion que sirve para eliminar una propiedad
     * 
     * @param f - archivo donde se encuentran las propiedades guardadas
     */
    public static void eliminarPropiedad(File f) {
        Properties config = cargar(f);

        System.out.println("Dime cual la key de la propiedad que quieres eliminar");
        String key = sc.nextLine();

        if (config.remove(key) != null) {
            System.out.println("Clave eliminado correctamente");
            FileWriter fw = null;
            try {
                fw = new FileWriter(f);
                config.store(fw, "Fichero de configuración");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            System.out.println("No hemos podido eliminar la propiedad");
        }

    }

    /**
     * Funcion que sirve para ver todas las propiedades
     * 
     * @param f - archivo donde se encuentran las propiedades guardadas
     */
    public static void mostrarPropiedades(File f) {
        Properties config = cargar(f);
        Enumeration<?> keys = config.propertyNames();
        boolean dentro = false;
        while (keys.hasMoreElements()) {
            if (!dentro) {
                dentro = true;
            }
            String key = (String) keys.nextElement();
            String value = config.getProperty(key);

            System.out.println("Clave: " + key + ", valor: " + value);
        }
        if (!dentro) {
            System.out.println("No tenemos valores para enseñar");
        }
    }

    /**
     * Funcion que sirve para cargar las propiedes
     * 
     * @param f - archivo donde se encuentran las propiedades guardadas
     * @return - devuelve las propiedades
     */
    private static Properties cargar(File f) {
        Properties config = new Properties();
        if (f.length() == 0) {
            return new Properties();
        }
        try {
            FileReader fr = new FileReader(f);
            config.load(fr);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Properties();
    }
}
