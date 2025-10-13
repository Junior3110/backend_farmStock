package com.FarmStock_Backend.FarmStock.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "herramienta")
public class Herramientas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_herramienta")
    private Integer idHerramienta;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede tener letras")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El estado no puede estar vacio")
    @Pattern(regexp = "^(Funcional|Mantenimiento|No_disponible)$", message = "El estado no es valido, deben de ser una de estas 3 (Funcional|Mantenimiento|No_disponible)")
    private String estado;

    @NotBlank(message = "El tipo no puede estar vacio")
    @Pattern(regexp = "^(Manual|Electrica)$", message = "El tipo no es valido, deben de ser una de estas 2 (Manual|Electrica)")
    private String tipo;

    @NotBlank(message = "La ubicación no puede estar vacio")
    @Pattern(regexp = "^(Bodega|Taller)$", message = "La ubicacion no es valido, deben de ser una de estas 2 (Bodega|Taller)")
    private String ubicacion;

    @NotBlank(message = "El numero de lote no puede estar vacio")
    @Pattern(regexp = "^(Lote 1|Lote 2)$", message = "El numero de lote no es valido, deben de ser una de estas 2 (Lote 1|Lote 2)")
    @JsonProperty("numero_lote")
    private String numero_lote;

    @NotNull(message = "La cantidad no puede estar vacía")
    private Integer cantidad;

    @NotNull(message = "La fecha de registro no puede ser nula")
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("fecha_registro")
    private LocalDate fecha_registro;

    public Herramientas(){}

     public Herramientas(String nombre, String descripcion, String estado, String tipo, 
                        String ubicacion, String numero_lote, Integer cantidad, 
                        LocalDate fecha_registro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.numero_lote = numero_lote;
        this.cantidad = cantidad;
        this.fecha_registro = fecha_registro;
    }
    // ===== Getters y Setters =====

    public Integer getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(Integer idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(String numero_lote) {
        this.numero_lote = numero_lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
