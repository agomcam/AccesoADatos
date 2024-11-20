package boletin1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorMySQL implements AutoCloseable {
    Connection connect = null;


    String schema;
    String url;
    String user;
    String pass;


    public ConectorMySQL(String schema, String url, String user, String pass) {
        this.schema = schema;
        this.url = schema + url;
        this.user = user;
        this.pass = pass;

        try {
            System.out.print("--- CONECTANDO A LA BD ---");
            connect = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println(this.url);
            System.out.println("---Conectado---");
        } catch (SQLException e) {
            System.err.println("Error al conectarse con la base de datos");
            e.printStackTrace();
        }
    }


    public Connection getConnect() {
        return connect;
    }

    public void Release() {
        try {
            if (connect != null && !connect.isClosed()) { // Verifica si la conexión no es nula y está abierta
                connect.close();
                System.out.println("--- Conexión cerrada con éxito ---");
            }
            user = null;
            pass = null;
            System.out.println("--- Parámetros liberados ---");
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión", e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            Release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
