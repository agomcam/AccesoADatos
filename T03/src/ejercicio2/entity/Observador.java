package ejercicio2.entity;


import javax.persistence.*;

@Entity
@Table(name = "OBSERVADOR")
public class Observador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_OBS")
    private Integer id;

    @Column(name = "DNI", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @Column(name = "DIRECCION", nullable = false, length = 30)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "NOMBRE_ASO")
    private Asociacion asociacion;

    @ManyToOne
    @JoinColumn(name = "CODIGO_Z", nullable = false)
    private ZonaObservacion zonaObservacion;

    @Column(name = "SITUACION", nullable = false, length = 8)
    private String situacion = "ACTIVO";

    public Observador() {
    }

    public Observador(String dni, String nombre, String direccion, Asociacion asociacion, ZonaObservacion zonaObservacion, String situacion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.asociacion = asociacion;
        this.zonaObservacion = zonaObservacion;
        this.situacion = situacion;
    }
// Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public ZonaObservacion getZonaObservacion() {
        return zonaObservacion;
    }

    public void setZonaObservacion(ZonaObservacion zonaObservacion) {
        this.zonaObservacion = zonaObservacion;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    @Override
    public String toString() {
        return "Observador{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", asociacion=" + asociacion +
                ", zonaObservacion=" + zonaObservacion +
                ", situacion='" + situacion + '\'' +
                '}';
    }
}