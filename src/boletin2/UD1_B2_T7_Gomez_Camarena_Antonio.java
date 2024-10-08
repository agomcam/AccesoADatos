package boletin2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UD1_B2_T7_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        ArrayList<Contacto> contContactos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "listaContactos.dat";
        File file = new File(rutaArchivo, nombreArchivo);
        int res = 2;

        // Comprobamos de que existe el archivo
        if (!file.exists()) {
            try {
                // En caso de que el archivo no exista lo creamos
                new File(rutaArchivo, nombreArchivo).createNewFile();
                System.out.println("Hemos tenido que crear el archivo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Inicializar la agenda");
        contContactos = recuperarContactos(file);

        while (res != 4) {

            System.out.println("Actualmente hay " + contContactos.size() + " Contactos almacenados");
            System.out.println("1) Mostrar Contactos\r\n" + //
                    "2) Añadir Contacto\r\n" + //
                    "3) Eliminar Contacto\r\n" + //
                    "4) Salir");
            res = sc.nextInt();
            sc.nextLine();

            switch (res) {
                case 1:
                    System.out.println("Mostramos los contactos agregados");
                    for (Contacto contacto : contContactos) {
                        System.out.println(contacto);
                    }
                    break;
                case 2:
                    Contacto c = new Contacto();
                    System.out.println("Para añadir un contacto tengo que pedirte datos sobre el: ");

                    System.out.println("Dime cual es su nombre:");
                    String datos = sc.nextLine();
                    c.setNombre(datos);

                    System.out.println("Dime cual es su apellido:");
                    datos = sc.nextLine();
                    c.setApellido(datos);

                    System.out.println("Dime cual es su número de teléfono:");
                    datos = sc.nextLine();
                    c.setTelefono(datos);

                    if (contContactos.contains(c)) {
                        System.out.println("El contacto ya existe");
                    } else {
                        contContactos.add(c);
                    }
                    break;
                case 3:
                    c = new Contacto();
                    System.out.println("Para eliminar un contacto tengo que pedirte datos sobre el: ");

                    System.out.println("Dime cual es su nombre:");
                    datos = sc.nextLine();
                    c.setNombre(datos);

                    System.out.println("Dime cual es su apellido:");
                    datos = sc.nextLine();
                    c.setApellido(datos);

                    System.out.println("Dime cual es su número de teléfono:");
                    datos = sc.nextLine();
                    c.setTelefono(datos);

                    System.out.println("Contacto elimiado: " + contContactos.remove(c));
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Lo siento pero no tenemos esta respuesta");
                    break;
            }
        }

        // Guardamos los datos de la agenda
        guardarContactos(file, contContactos);
        sc.close();
    }

    /**
     * Funcion que guarda un arraylist<Contacto> en un fichero binario
     * 
     * @param f         - fichero donde se van a guardar los datos
     * @param contactos - ArrayList de contactos que vamos a guardar
     */
    private static void guardarContactos(File f, ArrayList<Contacto> contactos) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(contactos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Funcion que recupera los datos de un archivo binario de un arraylist de
     * contactos
     * 
     * @param f - fichero del que queremos recuperar los datos
     * @return
     */
    @SuppressWarnings("unchecked")
    private static ArrayList<Contacto> recuperarContactos(File f) {
        ObjectInputStream ois = null;
        ArrayList<Contacto> contContactos = new ArrayList<Contacto>();

        try {
            if (f.length() > 0) {
                ois = new ObjectInputStream(new FileInputStream(f));
                contContactos = (ArrayList<Contacto>) ois.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contContactos;
    }
}
