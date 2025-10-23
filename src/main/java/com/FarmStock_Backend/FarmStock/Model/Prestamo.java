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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Integer idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "El usuario es requerido")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_detalle", nullable = false)
    @NotNull(message = "El detalle de la herramienta es requerido")
    private Herramienta_detalle herramientaDetalle;

    @NotNull(message = "La fecha de préstamo es requerida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @NotNull(message = "El estado es requerido")
    @Pattern(regexp = "^(Activo|Finalizado|Vencido)$", 
            message = "El estado debe ser: Activo, Finalizado o Vencido")
    @Column(nullable = false)
    private String estado;

    // Constructor vacío
    public Prestamo() {}

    // Constructor con parámetros
    public Prestamo(Usuario usuario, Herramienta_detalle herramientaDetalle, 
                   LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estado) {
        this.usuario = usuario;
        this.herramientaDetalle = herramientaDetalle;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Herramienta_detalle getHerramientaDetalle() {
        return herramientaDetalle;
    }

    public void setHerramientaDetalle(Herramienta_detalle herramientaDetalle) {
        this.herramientaDetalle = herramientaDetalle;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
