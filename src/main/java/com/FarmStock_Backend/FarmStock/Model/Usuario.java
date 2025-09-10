package com.FarmStock_Backend.FarmStock.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") 
    private Integer idUsuario;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras")
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo puede contener letras")
    private String apellidos;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no es válido")
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe tener entre 7 y 15 dígitos")
    private String telefono;

    @NotBlank(message = "El nombre de formación no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre de formación solo puede contener letras")
    private String nombreFormacion;   // <-- corregido a camelCase

    @NotBlank(message = "El número de ficha no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número de ficha debe contener solo dígitos")
    private String numeroFicha;       // <-- corregido

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número de documento debe contener solo dígitos")
    private String numeroDocumento;   // <-- corregido

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!¿?*.,:;_-]).{8,}$",
        message = "La contraseña debe tener mínimo 8 caracteres, incluir mayúscula, minúscula, número y carácter especial"
    )
    private String contrasena;

    @NotBlank(message = "El cargo no puede estar vacío")
    @Pattern(regexp = "^(instructor|aprendiz|administrador)$", message = "Cargo no válido. Opciones: Instructor, Aprendiz, Administrador")
    private String cargo;

    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Pattern(regexp = "^(CC|TI|CE|Pasaporte)$", message = "Tipo de documento no válido. Opciones: CC, TI, CE, Pasaporte")
    private String tipoDocumento;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Usuario() {}

    public Usuario(
        String nombres,
        String apellidos,
        String correo,
        String telefono,
        String nombreFormacion,
        String numeroFicha,
        String numeroDocumento,
        String cargo,
        String tipoDocumento,
        String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreFormacion = nombreFormacion;
        this.numeroFicha = numeroFicha;
        this.numeroDocumento = numeroDocumento;
        this.cargo = cargo;
        this.tipoDocumento = tipoDocumento;
        this.contrasena = contrasena;
    }

    // Getters y setters (ahora en camelCase)
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres.toLowerCase().trim(); }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos.toLowerCase().trim(); }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo.toLowerCase().trim(); }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getNombreFormacion() { return nombreFormacion; }
    public void setNombreFormacion(String nombreFormacion) { this.nombreFormacion = nombreFormacion.toLowerCase().trim(); }

    public String getNumeroFicha() { return numeroFicha; }
    public void setNumeroFicha(String numeroFicha) { this.numeroFicha = numeroFicha; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo.toLowerCase().trim(); }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
}
