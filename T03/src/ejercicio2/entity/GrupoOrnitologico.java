package ejercicio2.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GRUPO_ORNITOLOGICO")
public class GrupoOrnitologico {
    @Id
    @Column(name = "NOMBRE_G", length = 30)
    private String nombre;

    @Column(name = "PATAS", length = 30)
    private String patas;

    @Column(name = "DEDOS", length = 30)
    private String dedos;

    @Column(name = "PICO", length = 30)
    private String pico;

    public GrupoOrnitologico() {
    }

    public GrupoOrnitologico(String nombre, String patas, String dedos, String pico) {
        this.nombre = nombre;
        this.patas = patas;
        this.dedos = dedos;
        this.pico = pico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPatas() {
        return patas;
    }

    public void setPatas(String patas) {
        this.patas = patas;
    }

    public String getDedos() {
        return dedos;
    }

    public void setDedos(String dedos) {
        this.dedos = dedos;
    }

    public String getPico() {
        return pico;
    }

    public void setPico(String pico) {
        this.pico = pico;
    }
}