package ejercicio1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @Column(name = "proyecto_no")
    Integer proyecto_no;

    @Column(name = "pnombre")
    String pNombre;

    @OneToMany(mappedBy = "proyecto")
    private List<Trabaja> trabaja;

    public Proyecto() {
    }


    public void setProyecto_no(Integer proyecto_no) {
        this.proyecto_no = proyecto_no;
    }

    public List<Trabaja> getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(List<Trabaja> trabaja) {
        this.trabaja = trabaja;
    }

    public int getProyecto_no() {
        return proyecto_no;
    }

    public void setProyecto_no(int proyecto_no) {
        this.proyecto_no = proyecto_no;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }


    @Override
    public String toString() {
        return "Proyecto{" +
                "proyecto_no=" + proyecto_no +
                ", pNombre='" + pNombre + '\'' +
                '}';
    }


}
