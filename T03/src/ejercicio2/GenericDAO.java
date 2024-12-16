package ejercicio2;
import ejercicio1.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class GenericDAO<T> {

    private Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public void save(T entity) {
        Session session = HibernateUtils.openSession().getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    public T findById(int id) {
        Session session = HibernateUtils.openSession().getSession();
        return session.get(type, id);
    }

    public List<T> findAll() {
        Session session = HibernateUtils.openSession().getSession();
        return session.createQuery("from " + type.getName(), type).getResultList();
    }

    public void delete(T entity) {
        Session session = HibernateUtils.openSession().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }
}
