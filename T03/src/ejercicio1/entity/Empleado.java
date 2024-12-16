package ejercicio1.entity;

import ejercicio1.dto.EmpleadoDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    Integer emp_no; //PRIMARY KEY
    @Column(name = "apellido")
    String apellido;
    @Column(name = "oficio")
    String oficio;
    @OneToOne
    @JoinColumn(name = "dir", referencedColumnName = "emp_no")
    Empleado dir; //REFERENCES empleado(emp_no)
    @Column(name = "fecha_alt")
    Date fecha_alt;
    @Column(name = "salario")
    Float salario;
    @Column(name = "comision")
    Float comision;
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", nullable = false)
    Departamento dept; //REFERENCES departamento(dept_no)

    @OneToMany(mappedBy = "emp_no")
    private List<Trabaja> trabajos;

    //Constructor sin parametros
    public Empleado() {
    }

    //getters y setters


    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }


    public List<Trabaja> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabaja> trabajos) {
        this.trabajos = trabajos;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
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

    public Empleado getDir() {
        return dir;
    }

    public void setDir(Empleado dir) {
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

    public Departamento getDept() {
        return dept;
    }

    public void setDept(Departamento dept) {
        this.dept = dept;
    }

    //El resto es normal
    public Empleado(int emp_no, String apellido, String oficio, Empleado dir, Date fecha_alt,
                    Float salario, Float comision, Departamento dept) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Empleado [emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio +
                ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario +
                ", comision=" + comision + ", dept_no=" + dept + "]";
    }

    public EmpleadoDTO toDTO() {
        return new EmpleadoDTO(this);
    }
}