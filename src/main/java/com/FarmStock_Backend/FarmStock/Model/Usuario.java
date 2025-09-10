package com.FarmStock_Backend.FarmStock.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
    private String nombre_formacion;

    @NotBlank(message = "El número de ficha no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número de ficha debe contener solo dígitos")
    private String numero_ficha;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número de documento debe contener solo dígitos")
    private String numero_documento;

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
        String nombre_formacion,
        String numero_ficha,
        String numero_documento,
        String cargo,
        String tipoDocumento,
        String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.nombre_formacion = nombre_formacion;
        this.numero_ficha = numero_ficha;
        this.numero_documento = numero_documento;
        this.cargo = cargo;
        this.tipoDocumento = tipoDocumento;
        this.contrasena = contrasena;
    }

    // Getters y setters (puedes generarlos con Lombok si quieres simplificar)
    public int getId() {
        return idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres.toLowerCase().trim();
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.toLowerCase().trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo.toLowerCase().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre_formacion() {
        return nombre_formacion;
    }

    public void setNombre_formacion(String nombre_formacion) {
        this.nombre_formacion = nombre_formacion.toLowerCase().trim();
    }

    public String getNumero_ficha() {
        return numero_ficha;
    }

    public void setNumero_ficha(String numero_ficha) {
        this.numero_ficha = numero_ficha;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo.toLowerCase().trim();
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}
