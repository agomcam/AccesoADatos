package ejercicio1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "dept_no")
    private Integer dept_no; //PRIMARY KEY
    @Column(name = "dnombre")
    private String dnombre;
    @Column(name = "loc")
    private String loc;
    @OneToMany(mappedBy = "dept")
    public List<Empleado> empleados;

    //Constructor sin parametros
    public Departamento() {
    }

    //Getters y Setters
    public Integer getDept_no() {
        return dept_no;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setDept_no(Integer dept_no) {
        this.dept_no = dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    //El resto es normal
    public Departamento(Integer dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Departamento [dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + "]";
    }
}
