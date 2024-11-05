package boletin4;

import java.util.ArrayList;

public class Pelicula {
    String titulo;
    int duracion;
    String genero;
    String sinopsis;
    ArrayList<String> actores;
    int fecha;
    String director;

    public Pelicula() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<String> getActores() {
        return actores;
    }

    public void setActores(ArrayList<String> actores) {
        this.actores = actores;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero + ", sinopsis=" + sinopsis
                + ", actores=" + actores + ", fecha=" + fecha + ", director=" + director + "]";
    }

}
