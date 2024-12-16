package ejercicio1;


import ejercicio1.entity.Empleado;
import ejercicio1.hibernate.HibernateUtils;

public class main {
    public static void main(String[] args) {

        GenericDAO<Empleado> empleadoDAO = new GenericDAO<Empleado>(Empleado.class);
        // Probar idEmpleadosPorProyectosSQL
        System.out.println("--------------1------------");
        System.out.println(GenericDAO.idEmpleadosPorProyectosSQL(2));

        System.out.println("--------------2------------");
        System.out.println(GenericDAO.departamentosConSueldos());

        System.out.println("--------------3------------");
        System.out.println(GenericDAO.empleadosPorDepartamento(20));

        System.out.println("--------------4------------");
        System.out.println(GenericDAO.getDepartamentosImplicados(1));



        HibernateUtils.closeSessionFactory();

    }
}
