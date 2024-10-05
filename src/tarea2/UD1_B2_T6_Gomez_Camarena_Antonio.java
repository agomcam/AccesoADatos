package tarea2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UD1_B2_T6_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "agenda.dat";
        File file = new File(rutaArchivo, nombreArchivo);
        int res = 2;
        Agenda agendaContactos;

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
        agendaContactos = recuperarAgenda(file);
        while (res != 4) {
            int contador = 0;
            // Contamos cuantos contactos tenemos en la agenda
            for (Contacto cont : agendaContactos.getContacto()) {
                if (cont != null) {
                    contador++;
                }
            }
            System.out.println("Actualmente hay " + contador + " Contactos almacenados");
            System.out.println("1) Mostrar Contactos\r\n" + //
                    "2) Añadir Contacto\r\n" + //
                    "3) Eliminar Contacto\r\n" + //
                    "4) Salir");
            res = sc.nextInt();
            sc.nextLine();

            switch (res) {
                case 1:
                    agendaContactos.mostrarContactos();
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

                    agendaContactos.annadeContacto(c);
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

                    agendaContactos.elimarContacto(c);
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
        guardarAgenda(file, agendaContactos);
        sc.close();
    }

    /**
     * Funcion que guarda la agenda en un fichero binario
     * 
     * @param f - fichero donde se van a guardar los datos
     * @param a - agenda que queremos guardar
     */
    private static void guardarAgenda(File f, Agenda a) {
        ObjectOutputStream oos = null;
        System.out.println(a);
        try {
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(a);
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
     * Funcion que recupera los datos de un archivo binario de una agenda
     * 
     * @param f - fichero del que queremos recuperar los datos
     * @return
     */
    private static Agenda recuperarAgenda(File f) {
        ObjectInputStream ois = null;
        Agenda agenda = null;
        if (f.length() == 0) {
            agenda = new Agenda();
            return agenda;
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            agenda = (Agenda) ois.readObject();

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

        return agenda;
    }
}
