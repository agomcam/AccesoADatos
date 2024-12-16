package boletin2;

import java.sql.*;

import boletin2.ConectorMySQL;
import boletin2.Departamento;
import boletin2.Empleado;

public class UD2_B3_mainHuecos {
    private static ConectorMySQL miConector;

    public static void main(String[] args) {

        // Conectar con la BD
        miConector = new ConectorMySQL("jdbc:mysql://localhost/", "proyectos", "adminProyectos", "adminProyectos1234");

        // Usar la conexion
        if (miConector.getConnect() != null) {
            try {
                System.out.println("--- Departamento 10 -------------------");
                System.out.println("--- Sin preparar: " + UD2_B3_getDepartamento("10"));
                System.out.println("--- 1) Sin preparar Injection: " + UD2_B3_T1_getDepartamento_SQLInjection("10"));
                System.out.println("--- 2) Preparada: " + UD2_B3_T2_getDepartamentoPreparado("10"));
                System.out.println("--- 3) Preparada Injection: " + UD2_B3_T3_getDepartamentoPreparado_SQLInjection("10"));
                System.out.println("--- 4) Para que se usan las consultas sin preparar?");
                System.out.println("---------------------------------------");
                System.out.println("--- Empleado 7369 -------------------");
                System.out.println("--- 5) Preparada: " + UD2_B3_T5_getEmpleadoPreparado("7369"));
                System.out.println("--- 6) Mostrar MetaDatos");
                UD2_B3_T6_getEmpleadoPreparadoMetadatos("7369");
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


    private static Departamento UD2_B3_getDepartamento(String _dept_no) throws SQLException {
        //System.out.println("- getDepartamento(" + _dept_no + ") -");
        Statement sentenciaDepartamentos = miConector.getConnect().createStatement(); // Abrir sentencia
        String sqlSelect = "select *" + "from departamento " + "where dept_no=" + _dept_no + ";";
        // System.out.println("Consulta: " + sqlSelect);
        ResultSet resultadoDepartamentos = sentenciaDepartamentos.executeQuery(sqlSelect); // Abrir ResultSet

        if (resultadoDepartamentos.next()) {// Recorrer ResultSet
            int dept_no = resultadoDepartamentos.getInt("dept_no");
            String dnombre = resultadoDepartamentos.getString("dnombre");
            String loc = resultadoDepartamentos.getString("loc");

            resultadoDepartamentos.close();// Cerrar ResultSet
            sentenciaDepartamentos.close();// Cerrar Sentencia

            return new Departamento(dept_no, dnombre, loc);
        }

        resultadoDepartamentos.close();// Cerrar ResultSet
        sentenciaDepartamentos.close();// Cerrar Sentencia

        return null;
    }

    private static Departamento UD2_B3_T1_getDepartamento_SQLInjection(String _dept_no) throws SQLException {
        return UD2_B3_getDepartamento(_dept_no + " or 1=1 order by dept_no desc");
    }

    private static String UD2_B3_T2_getDepartamentoPreparado(String _dept_no) throws SQLException {
        // TODO
        //System.out.println("- getDepartamento(" + _dept_no + ") -");
        String sqlSelect = "select * from departamento where dept_no = ? ;";
        // System.out.println("Consulta: " + sqlSelect);
        PreparedStatement ps = miConector.getConnect().prepareStatement(sqlSelect);
        ps.setString(1, _dept_no);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {// Recorrer ResultSet
            int dept_no = resultSet.getInt("dept_no");
            String dnombre = resultSet.getString("dnombre");
            String loc = resultSet.getString("loc");

            resultSet.close();// Cerrar ResultSet
            ps.close();// Cerrar Sentencia

            return (new Departamento(dept_no, dnombre, loc)).toString();
        }

        resultSet.close();// Cerrar ResultSet
        ps.close();// Cerrar Sentencia

        return null;
    }

    private static String UD2_B3_T3_getDepartamentoPreparado_SQLInjection(String _dept_no) {
        try {
            return UD2_B3_T2_getDepartamentoPreparado(_dept_no);// TODO
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Empleado UD2_B3_T5_getEmpleadoPreparado(String _emp_no) {
        String sql = "select * from empleado where emp_no = ?;";
        try {
            PreparedStatement ps = miConector.getConnect().prepareStatement(sql);
            ps.setString(1, _emp_no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int emp_no = rs.getInt(1);
                String apellido = rs.getString(2);
                String oficio = rs.getString(3);
                int dir = rs.getInt(4);
                Date fechaAlta = rs.getDate(5);
                float salario = rs.getFloat(6);
                float comision = rs.getFloat(7);
                int depNo = rs.getInt(8);

                return new Empleado(emp_no, apellido, oficio, dir, fechaAlta, salario, comision, depNo);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static void UD2_B3_T6_getEmpleadoPreparadoMetadatos(String _emp_no) {
        String sql = "select * from empleado where emp_no = ?;";
        try {
            PreparedStatement ps = miConector.getConnect().prepareStatement(sql);
            ps.setString(1, _emp_no);
            ResultSet rs = ps.executeQuery();
            // Obtener los metadatos del ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            System.out.println("Número de columnas: " + columnCount);
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Columna " + i + ":");
                System.out.println("  Nombre: " + rsmd.getColumnName(i));
                System.out.println("  Tipo: " + rsmd.getColumnTypeName(i));
                System.out.println("  Tamaño: " + rsmd.getColumnDisplaySize(i));
                System.out.println("  Es nullable: " + rsmd.isNullable(i));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
