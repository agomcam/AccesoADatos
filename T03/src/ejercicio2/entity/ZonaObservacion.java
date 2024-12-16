package ejercicio2.entity;

import javax.persistence.*;

@Entity
@Table(name = "ZONA_OBSERVACION")
public class ZonaObservacion {

    @Id
    @Column(name = "CODIGO_Z", length = 10)
    private String codigo;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "PROVINCIA", nullable = false)
    private Provincia provincia;

    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;

    public ZonaObservacion() {
    }

    public ZonaObservacion(String codigo, String nombre, Provincia provincia, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Zona_Observacion{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", provincia=" + provincia +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
