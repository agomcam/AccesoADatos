package ejercicio2;

import ejercicio2.entity.*;
import ejercicio2.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
        // Vaciar la base de datos
        vaciarBD();

        // Llenar la base de datos
        llenarBD();
    }

    // Vaciar la base de datos
    public static void vaciarBD() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Eliminar todos los datos de las tablas (realizando consultas SQL directas)
            session.createSQLQuery("DELETE FROM OBSERVACION").executeUpdate();
            session.createSQLQuery("DELETE FROM ESPECIE").executeUpdate();
            session.createSQLQuery("DELETE FROM OBSERVADOR").executeUpdate();
            session.createSQLQuery("DELETE FROM ZONA_OBSERVACION").executeUpdate();
            session.createSQLQuery("DELETE FROM ASOCIACION").executeUpdate();
            session.createSQLQuery("DELETE FROM GRUPO_ORNITOLOGICO").executeUpdate();
            session.createSQLQuery("DELETE FROM PROVINCIA").executeUpdate();

            // Commit de la transacción
            transaction.commit();
            System.out.println("Base de datos vaciada correctamente.");
        } catch (Exception e) {
            // Si ocurre algún error, hacer rollback
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }

    // Llenar la base de datos
    public static void llenarBD() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Crear e insertar provincias
            Provincia provincia1 = new Provincia("Madrid", "Comunidad de Madrid");
            Provincia provincia2 = new Provincia("Barcelona", "Cataluña");
            Provincia provincia3 = new Provincia("Sevilla", "Andalucía");
            Provincia provincia4 = new Provincia("Valencia", "Comunidad Valenciana");
            Provincia provincia5 = new Provincia("Zaragoza", "Aragón");

            session.save(provincia1);
            session.save(provincia2);
            session.save(provincia3);
            session.save(provincia4);
            session.save(provincia5);

            // Crear e insertar zonas de observación
            ZonaObservacion zona1 = new ZonaObservacion("Z001", "Zona Norte", provincia1, "Tipo A");
            ZonaObservacion zona2 = new ZonaObservacion("Z002", "Zona Sur", provincia2, "Tipo B");
            ZonaObservacion zona3 = new ZonaObservacion("Z003", "Zona Este", provincia3, "Tipo A");
            ZonaObservacion zona4 = new ZonaObservacion("Z004", "Zona Oeste", provincia4, "Tipo C");
            ZonaObservacion zona5 = new ZonaObservacion("Z005", "Zona Centro", provincia5, "Tipo B");

            session.save(zona1);
            session.save(zona2);
            session.save(zona3);
            session.save(zona4);
            session.save(zona5);

            // Crear e insertar asociaciones
            Asociacion aso1 = new Asociacion("Asociacion A", "Calle 1", 1234567890L);
            Asociacion aso2 = new Asociacion("Asociacion B", "Calle 2", 9876543210L);
            Asociacion aso3 = new Asociacion("Asociacion C", "Calle 3", 1122334455L);
            Asociacion aso4 = new Asociacion("Asociacion D", "Calle 4", 6677889900L);
            Asociacion aso5 = new Asociacion("Asociacion E", "Calle 5", 5544332211L);

            session.save(aso1);
            session.save(aso2);
            session.save(aso3);
            session.save(aso4);
            session.save(aso5);

            // Crear e insertar grupos ornitológicos
            GrupoOrnitologico grupo1 = new GrupoOrnitologico("Grupo A", "Cuatro", "Cinco", "Curvado");
            GrupoOrnitologico grupo2 = new GrupoOrnitologico("Grupo B", "Tres", "Cuatro", "Recto");
            GrupoOrnitologico grupo3 = new GrupoOrnitologico("Grupo C", "Dos", "Tres", "Largo");
            GrupoOrnitologico grupo4 = new GrupoOrnitologico("Grupo D", "Cinco", "Seis", "Ancho");
            GrupoOrnitologico grupo5 = new GrupoOrnitologico("Grupo E", "Cuatro", "Cinco", "Redondeado");

            session.save(grupo1);
            session.save(grupo2);
            session.save(grupo3);
            session.save(grupo4);
            session.save(grupo5);

            // Crear e insertar especies
            Especie especie1 = new Especie("Avescientifica1", "Ave Vulgar 1", "Descripción 1", grupo1);
            Especie especie2 = new Especie("Avescientifica2", "Ave Vulgar 2", "Descripción 2", grupo2);
            Especie especie3 = new Especie("Avescientifica3", "Ave Vulgar 3", "Descripción 3", grupo3);
            Especie especie4 = new Especie("Avescientifica4", "Ave Vulgar 4", "Descripción 4", grupo4);
            Especie especie5 = new Especie("Avescientifica5", "Ave Vulgar 5", "Descripción 5", grupo5);

            session.save(especie1);
            session.save(especie2);
            session.save(especie3);
            session.save(especie4);
            session.save(especie5);

            // Crear e insertar observadores
            Observador obs1 = new Observador("12345678A", "Juan Pérez", "Calle 10", aso1, zona1, "ACTIVO");
            Observador obs2 = new Observador("23456789B", "Ana López", "Calle 20", aso2, zona2, "ACTIVO");
            Observador obs3 = new Observador("34567890C", "Carlos García", "Calle 30", aso3, zona3, "INACTIVO");
            Observador obs4 = new Observador("45678901D", "María Martín", "Calle 40", aso4, zona4, "ACTIVO");
            Observador obs5 = new Observador("56789012E", "Luis Gómez", "Calle 50", aso5, zona5, "INACTIVO");

            session.save(obs1);
            session.save(obs2);
            session.save(obs3);
            session.save(obs4);
            session.save(obs5);

            // Crear e insertar observaciones
            Observacion obs1_1 = new Observacion(especie1, obs1, java.sql.Date.valueOf("2024-01-01"));
            Observacion obs2_1 = new Observacion(especie2, obs2, java.sql.Date.valueOf("2024-01-02"));
            Observacion obs3_1 = new Observacion(especie3, obs3, java.sql.Date.valueOf("2024-01-03"));
            Observacion obs4_1 = new Observacion(especie4, obs4, java.sql.Date.valueOf("2024-01-04"));
            Observacion obs5_1 = new Observacion(especie5, obs5, java.sql.Date.valueOf("2024-01-05"));

            session.save(obs1_1);
            session.save(obs2_1);
            session.save(obs3_1);
            session.save(obs4_1);
            session.save(obs5_1);

            // Commit de la transacción
            transaction.commit();
            System.out.println("Base de datos llenada correctamente.");
        } catch (Exception e) {
            // Si ocurre algún error, hacer rollback
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }
}
