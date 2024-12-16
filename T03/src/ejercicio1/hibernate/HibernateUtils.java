package ejercicio1.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HibernateUtils {
    // Cargar el archivo de configuracion
    static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("./hibernate.cfg.xml").build();
    // Configurar la conexion
    static final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    public static Session openSession(){
// Abre una nueva sesion
        return sessionFactory.openSession();
    }
    public static void closeSessionFactory(){
        if (sessionFactory != null)
            sessionFactory.close();
    }
}