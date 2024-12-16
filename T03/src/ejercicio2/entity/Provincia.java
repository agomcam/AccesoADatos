package ejercicio2.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVINCIA")
public class Provincia {
    @Id
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @Column(name = "COMUNIDAD", nullable = false, length = 30)
    private String comunidad;

    public Provincia() {
    }

    public Provincia(String nombre, String comunidad) {
        this.nombre = nombre;
        this.comunidad = comunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "nombre='" + nombre + '\'' +
                ", comunidad='" + comunidad + '\'' +
                '}';
    }
}
