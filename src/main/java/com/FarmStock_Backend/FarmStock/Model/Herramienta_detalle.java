package com.FarmStock_Backend.FarmStock.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "herramienta_detalle")
public class Herramienta_detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    // Relación con herramienta (FK)
    @ManyToOne
    @JoinColumn(name = "id_herramienta", nullable = false)
    private Herramientas herramienta;

    @NotBlank(message = "El código único no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "El código único solo puede contener letras, números, guiones o guiones bajos")
    @Column(name = "codigo_unico", length = 50, nullable = false, unique = true)
    private String codigoUnico;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(
        regexp = "^(Disponible|No_disponible|Mantenimiento)$",
        message = "El estado no es válido. Debe ser uno de: Disponible, Dañado o En_reparacion"
    )
    private String estado;

    @NotNull(message = "El campo disponible no puede ser nulo")
    @Column(nullable = false)
    private Boolean disponible;

    @NotNull(message = "La fecha de ingreso no puede ser nula")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    // ===== Constructores =====
    public Herramienta_detalle() {}

    public Herramienta_detalle(Herramientas herramienta, String codigoUnico, String estado, Boolean disponible, LocalDate fechaIngreso) {
        this.herramienta = herramienta;
        this.codigoUnico = codigoUnico;
        this.estado = estado;
        this.disponible = disponible;
        this.fechaIngreso = fechaIngreso;
    }

    // ===== Getters y Setters =====
    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Herramientas getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramientas herramienta) {
        this.herramienta = herramienta;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
