package ejercicio1.dto;

import ejercicio1.entity.Empleado;

import java.util.Date;

public class EmpleadoDTO {
    Integer emp_no; //PRIMARY KEY
    String apellido;
    String oficio;
    Integer dir; //REFERENCES empleado(emp_no),
    Date fecha_alt;
    Float salario;
    Float comision;
    Integer dept; //REFERENCES departamento(dept_no)

    //Constructor sin parametros
    public EmpleadoDTO() {
    }

    //Constructor con parametros
    public EmpleadoDTO(Integer emp_no, String apellido, String oficio, Integer
            dir, Date fecha_alt, Float salario, Float comision, Integer dept) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept = dept;
    }

    //Constructor con parametro Clase
    public EmpleadoDTO(Empleado emp) {
        this.emp_no = emp.getEmp_no();
        this.apellido = emp.getApellido();
        this.oficio = emp.getOficio();
        this.dir = emp.getDir().getEmp_no();
        this.fecha_alt = emp.getFecha_alt();
        this.salario = emp.getSalario();
        this.comision = emp.getComision();
        this.dept = emp.getDept().getDept_no();
    }
//getters y setters

    public Integer getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Integer getDir() {
        return dir;
    }

    public void setDir(Integer dir) {
        this.dir = dir;
    }

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public void setFecha_alt(Date fecha_alt) {
        this.fecha_alt = fecha_alt;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "emp_no=" + emp_no +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", dir=" + dir +
                ", fecha_alt=" + fecha_alt +
                ", salario=" + salario +
                ", comision=" + comision +
                ", dept=" + dept +
                '}';
    }

}
