package boletin1;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UD2_B2_mainHuecos {
    private static ConectorMySQL miConector;

    public static void main(String[] args) {

        // Conectar con la BD
        miConector = new ConectorMySQL("jdbc:mysql://localhost/", "proyectos", "adminProyectos", "adminProyectos1234");

        // Usar la conexion
        if (miConector.getConnect() != null) {
            try {
                System.out.println("---------------------------------------");
                UD2_B2_T3_MostrarDepartamentos();
                System.out.println("---------------------------------------");
                System.out.println("--- Departamento 10: " + UD2_B2_T4_getDepartamento("10"));
                System.out.println("--- Departamento 0: " + UD2_B2_T4_getDepartamento("0"));
                System.out.println("---------------------------------------");
                UD2_B2_T5_ReducirSalario("7369", 0.2);
                UD2_B2_T5_ReducirSalario("7369", -0.25);
                System.out.println("---------------------------------------");
                UD2_B2_T6_CadenaDeDireccion();


            } catch (SQLException e) {
                System.err.println("Error en la conexion de la BD");
                e.printStackTrace();
            }

        } else {
            System.err.println("Error en la conexión");
        }

        // Liberar los recursos
        miConector.Release();
    }// Fin main

    private static void UD2_B2_T3_MostrarDepartamentos() throws SQLException {
        System.out.println("- INFORMACION DE LOS DEPARTAMENTOS -");
        Statement statement = miConector.connect.createStatement();
        String sql = "select d.dept_no, d.dnombre, d.loc, sum(e.dept_no) from departamento d left join  empleado e on d.dept_no = e.dept_no group by d.dept_no, d.dnombre, d.loc;";
        ResultSet rs = statement.executeQuery(sql);


        while (rs.next()) {
            int dep_no = rs.getInt("dept_no");
            System.out.print("--- dept_no: " + dep_no);
            String dnombre = rs.getString("dnombre");
            System.out.print("\tdnombre: " + dnombre);
            String loc = rs.getString("loc");
            System.out.println("\tloc: " + loc);
            System.out.println("\tCantidad empleados: " + rs.getString(4));
        }

        rs.close();
        statement.close();
    }


    private static Departamento UD2_B2_T4_getDepartamento(String _dept_no) throws SQLException {
        System.out.println("- getDepartamento(" + _dept_no + ") -");
        Statement statement = miConector.getConnect().createStatement();
        String sql = "select * from departamento where dept_no = " + _dept_no;
        ResultSet rs = statement.executeQuery(sql);


        Departamento departamento = null;
        if (rs.next()) {
            int dep_no = rs.getInt("dept_no");
            System.out.print("--- dept_no: " + dep_no);
            String dnombre = rs.getString("dnombre");
            System.out.print("\tdnombre: " + dnombre);
            String loc = rs.getString("loc");
            System.out.println("\tloc: " + loc);

            departamento = new Departamento(dep_no, dnombre, loc);

            rs.close();
            statement.close();
            return departamento;
        }
        rs.close();
        statement.close();
        return null;
    }

    private static Empleado UD2_B2_T5_getEmpleado(String _emp_no) throws SQLException {
        String sql = "select * from empleado where emp_no = " + _emp_no;

        Statement statement = miConector.connect.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            int emp_no = rs.getInt(1);
            String apellido = rs.getString(2);
            String oficio = rs.getString(3);
            int dir = rs.getInt(4);
            Date fecha_alt = rs.getDate(5);
            float salario = rs.getFloat(6);
            float comision = rs.getFloat(7);
            int dept_no = rs.getInt(8);

            rs.close();
            statement.close();
            return new Empleado(emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no);
        }
        rs.close();
        statement.close();
        return null;
    }

    private static void UD2_B2_T5_ReducirSalario(String _emp_no, double _desc) throws SQLException {
        System.out.println("- ReducirSalario(" + _emp_no + ", " + _desc + ")");
        String sql;
        Empleado empleado = UD2_B2_T5_getEmpleado(_emp_no);

        if (empleado != null) {
            if (_desc > 0 && _desc < 1) {
                // Calculamos el nuevo salario después de aplicar el descuento
                double nuevoSalario = empleado.salario * _desc;
                sql = "update empleado set salario = " + nuevoSalario + " where emp_no = " + _emp_no;

                Statement statement = miConector.getConnect().createStatement();
                statement.executeUpdate(sql);

                System.out.println(empleado.apellido + "\nSalario antes de reducir: " + empleado.salario +
                        "\nSalario ahora: " + nuevoSalario);
                statement.close();
            } else {
                System.out.println("Error: El valor de descuento debe estar entre 0 y 1.");
            }
        }
    }


    private static void UD2_B2_T6_CadenaDeDireccion() throws SQLException {
        String sql = "select * from empleado;";
        Statement statement1 = miConector.getConnect().createStatement();
        ResultSet rs1 = statement1.executeQuery(sql);

        System.out.println("- CadenaDeJefes()");

        while (rs1.next()) {
            String empleadoNombre = rs1.getString(2);
            String jefeId = rs1.getString(4);
            System.out.print(empleadoNombre);

            // Mostrar la cadena completa de jefes
            while (jefeId != null) {
                String sql2 = "select * from empleado where emp_no = " + jefeId;


                Statement statement2 = miConector.getConnect().createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);

                if (rs2.next()) {
                    String jefeNombre = rs2.getString(2);
                    jefeId = rs2.getString(4);
                    System.out.print(" > " + jefeNombre);
                } else {
                    jefeId = null;
                }


                rs2.close();
                statement2.close();
            }

            System.out.println();
        }


        rs1.close();
        statement1.close();
    }


}// Fin UD2_B2_main