package ejercicio1.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trabaja")
public class Trabaja implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    Empleado emp_no;

    @Id
    @ManyToOne
    @JoinColumn(name = "proyecto_no", referencedColumnName = "proyecto_no")
    Proyecto proyecto;

    Integer horas;

    public Empleado getEmp_no() {

        return emp_no;
    }

    public void setEmp_no(Empleado emp_no) {
        this.emp_no = emp_no;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    @Override
    public String
    toString() {
        return "Trabaja{" +
                "emp_no=" + emp_no +
                ", proyecto=" + proyecto +
                ", horas=" + horas +
                '}';
    }
}
