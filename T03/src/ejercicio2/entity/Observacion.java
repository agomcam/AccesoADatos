package ejercicio2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OBSERVACION")
public class Observacion implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "NOMBRE_CIENTIFICO")
    private Especie especie;

    @Id
    @ManyToOne
    @JoinColumn(name = "COD_OBS")
    private Observador observador;

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observacion that = (Observacion) o;
        return Objects.equals(especie, that.especie) && Objects.equals(observador, that.observador) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especie, observador, fecha);
    }

    public Observacion() {
    }

    public Observacion(Especie especie, Observador observador, Date fecha) {
        this.especie = especie;
        this.observador = observador;
        this.fecha = fecha;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Observador getObservador() {
        return observador;
    }

    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}