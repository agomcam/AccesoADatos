package ejercicio1.dto;

import ejercicio1.entity.Proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProyectoDTO {
    private Integer proyectoNo;
    private String pNombre;
    private List<EmpleadoDTO> empleados;
    private List<Integer> horas;

    // Constructor que toma una entidad Proyecto y construye el DTO


    public ProyectoDTO() {
    }

    public ProyectoDTO(Proyecto proyecto) {
        this.proyectoNo = proyecto.getProyecto_no();
        this.pNombre = proyecto.getpNombre();
        this.empleados = proyecto.getTrabaja().stream()
                .map(trabajo -> new EmpleadoDTO(trabajo.getEmp_no()))
                .collect(Collectors.toList());
        this.horas = proyecto.getTrabaja().stream()
                .map(trabajo -> trabajo.getHoras())
                .collect(Collectors.toList());
    }

    public ProyectoDTO(Integer proyectoNo, String pNombre, List<EmpleadoDTO> empleados, List<Integer> horas) {
        this.proyectoNo = proyectoNo;
        this.pNombre = pNombre;
        this.empleados = empleados;
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "proyectoNo=" + proyectoNo +
                ", pNombre='" + pNombre + '\'' +
                ", empleados=" + empleados +
                ", horas=" + horas +
                '}';
    }

    // Getters y Setters
    public Integer getProyectoNo() {
        return proyectoNo;
    }

    public void setProyectoNo(Integer proyectoNo) {
        this.proyectoNo = proyectoNo;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<Integer> getHoras() {
        return horas;
    }

    public void setHoras(List<Integer> horas) {
        this.horas = horas;
    }
}