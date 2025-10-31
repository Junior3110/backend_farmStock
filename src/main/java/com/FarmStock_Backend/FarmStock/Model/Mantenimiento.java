package com.FarmStock_Backend.FarmStock.Model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;

    @NotNull
    @Column(name = "id_herramienta")
    private Integer idHerramienta;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_mantenimiento")
    private LocalDate fechaMantenimiento;

    @Column(name = "realizado_por")
    private Integer realizadoPor; // id_usuario

    @Column(name = "id_detalle")
    private Integer idDetalle; // id_detalle de herramienta_detalle

    public Mantenimiento() {}

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Integer getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(Integer idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion.trim() : null;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public Integer getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(Integer realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
}