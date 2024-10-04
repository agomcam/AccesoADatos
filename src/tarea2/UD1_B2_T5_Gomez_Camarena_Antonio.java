package tarea2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UD1_B2_T5_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        Pedido arrayPedidos1[] = {
                new Pedido("Carta pokemon", 10, 6.56),
                new Pedido("Peluche tamaño xxl", 1, 50),
                new Pedido("Almohada", 2, 5.20) };

        Pedido arrayPedidos2[] = {
                new Pedido("Laptop gaming", 2, 1200.99),
                new Pedido("Cámara fotográfica", 3, 350.75),
                new Pedido("Silla ergonómica", 1, 199.99)
        };

        String rutaArchivo = System.getProperty("user.home") + "\\AD";
        String nombreArchivo = "pedidos.dat";
        File file = new File(rutaArchivo, nombreArchivo);
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

        escribirPedidos(arrayPedidos1, file);
        leerPedidos(file);

        annadePedidos(arrayPedidos2, file);

        leerPedidos(file);
    }

    /**
     * Funcion que añade a un archivo binario un pedido
     * 
     * @param pedidos - array de pedidos que queremos añadir
     * @param f       - fichero donde se encuentra el pedido
     */
    private static void escribirPedidos(Pedido[] pedidos, File f) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

            for (Pedido pedido : pedidos) {
                oos.writeObject(pedido);
            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcion que lee los pedidos de un fichero que le pasemos por parametro
     * 
     * @param f - fichero del cual queremos ver los pedidos que contiene
     */
    private static void leerPedidos(File f) {
        int i = 1;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));

            while (true) {

                Pedido p = (Pedido) ois.readObject();

                System.out.println("Pedido " + i + ": " + p.toString());
                i++;
            }

        } catch (IOException e) {
            System.out.println("Fichero leido exitosamente");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Funcion que añade a un archivo binario un pedido
     * 
     * @param pedidos - array de pedidos que queremos añadir
     * @param f       - fichero donde se encuentra el pedido
     */
    private static void annadePedidos(Pedido[] pedidos, File f) {
        ObjectOutputStream oos = null;
        try {
            if (f.exists()) {
                oos = new MiObjectOutputStream(new FileOutputStream(f,true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            for (Pedido pedido : pedidos) {
                oos.writeObject(pedido);
            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
