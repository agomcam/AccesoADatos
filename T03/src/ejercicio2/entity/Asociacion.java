package ejercicio2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASOCIACION")
public class Asociacion {
    @Id
    @Column(name = "NOMBRE_ASO", length = 30)
    private String nombre;

    @Column(name = "DIRECCION", nullable = false, length = 30)
    private String direccion;

    @Column(name = "TELEFONO", nullable = false, unique = true)
    private Long telefono;

    public Asociacion() {
    }

    public Asociacion(String nombre, String direccion, Long telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
