
package tarea2;

import java.io.Serializable;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private Integer numUnidades;
    private double precio;

    public Pedido() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param descripcion
     * @param numUnidades
     * @param precio
     */
    public Pedido(String descripcion, Integer numUnidades, double precio) {
        this.descripcion = descripcion;
        this.numUnidades = numUnidades;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(Integer numUnidades) {
        this.numUnidades = numUnidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pedidos [descripcion=" + descripcion + ", numUnidades=" + numUnidades + ", precio=" + precio + "]";
    }

}
