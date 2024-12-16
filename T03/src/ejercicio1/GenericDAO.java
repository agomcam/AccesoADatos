package ejercicio1;

import ejercicio1.entity.Departamento;
import ejercicio1.entity.Empleado;
import ejercicio1.entity.Proyecto;
import ejercicio1.entity.Trabaja;
import ejercicio1.hibernate.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class GenericDAO<T> {
    Class<T> type;

    public GenericDAO(Class<T> obj) {
        this.type = obj;
    }

    public T get(int id) {
        Session session = HibernateUtils.openSession();
        T obj = session.get(type, id);
        session.close();
        return obj;
    }

    public List<T> getAll() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction(); //Inicia la transaccion
        List<T> objList = (List<T>) session.createCriteria(type).list();
        session.getTransaction().commit(); //Valida la transacción
        session.close();
        return objList;
    }

    public void save(T obj) {
        Session session = HibernateUtils.openSession();
        session.beginTransaction(); //Inicia la transaccion
        session.save(obj);
        session.getTransaction().commit(); //Valida la transacción
        session.close();
    }

    public void update(T obj) {
        Session session = HibernateUtils.openSession();
        session.beginTransaction(); //Inicia la transaccion
        session.update(obj);
        session.getTransaction().commit(); //Valida la transacción
        session.close();
    }

    public void delete(T obj) {
        Session session = HibernateUtils.openSession();
        session.beginTransaction(); //Inicia la transaccion
        session.delete(obj);
        session.getTransaction().commit(); //Valida la transacción
        session.close();
    }
// FUNCIONES ADICIONALES // TODO


    public static List<Integer> idEmpleadosPorProyectosSQL(int proyecto_no) {
        Session session = HibernateUtils.openSession();
        Query query = session.createNativeQuery("SELECT e.emp_no FROM trabaja t LEFT JOIN empleado e ON t.emp_no = e.emp_no WHERE t.proyecto_no = :proyecto_no");
        query.setParameter("proyecto_no", proyecto_no);
        List<Integer> result = query.list();

        return result;
    }

    public static HashMap<Integer, Double> departamentosConSueldos() {
        Session session = HibernateUtils.openSession();
        HashMap<Integer, Double> result = new HashMap<>();

        try {
            session.beginTransaction();
            String query = "SELECT e.dept.dept_no, SUM(e.salario) FROM Empleado e GROUP BY e.dept.dept_no ORDER BY SUM(e.salario) DESC";
            List<Object[]> resultList = session.createQuery(query).getResultList();
            for (Object[] row : resultList) {
                result.put((Integer) row[0], (Double) row[1]);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return result;

    }

    public static List<Empleado> empleadosPorDepartamento(int dept_no) {
        Session session = HibernateUtils.openSession();
        List<Empleado> empleados = new ArrayList<>();
        try {
            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, dept_no);
            if (departamento != null) {
                Hibernate.initialize(departamento.getEmpleados()); // Inicializa la colección
                empleados = departamento.getEmpleados();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return empleados;
    }


    public static List<Departamento> getDepartamentosImplicados(int proyecto_no) {
        Session session = HibernateUtils.openSession();
        List<Departamento> departamentos = new ArrayList<>();
        try {
            session.beginTransaction();
            Proyecto proyecto = session.get(Proyecto.class, proyecto_no);
            if (proyecto != null) {
                Set<Departamento> deptSet = new HashSet<>();
                for (Trabaja trabaja : proyecto.getTrabaja()) {
                    deptSet.add(trabaja.getEmp_no().getDept());
                }
                departamentos = new ArrayList<>(deptSet);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return departamentos;
    }
}