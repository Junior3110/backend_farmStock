package com.FarmStock_Backend.FarmStock.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Prestamo;
import com.FarmStock_Backend.FarmStock.Service.PrestamoLogica;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoLogica prestamoLogica;

    @Autowired
    public PrestamoController(PrestamoLogica prestamoLogica) {
        this.prestamoLogica = prestamoLogica;
    }

    // Crear un préstamo (requiere un código de herramienta y el objeto préstamo)
    @PostMapping("/crear")
    public ResponseEntity<Prestamo> crearPrestamo(@RequestParam String codigoUnico,@RequestParam Integer idUsuario, @RequestParam String numeroDocumento, @RequestBody Prestamo prestamo) {
        Prestamo creado = prestamoLogica.crearPrestamo(codigoUnico, idUsuario, numeroDocumento,  prestamo);
        
        return ResponseEntity.ok(creado);
    }

    // Listar todos los préstamos
    @GetMapping("/todos")
    public ResponseEntity<List<Prestamo>> listarTodosLosPrestamos() {
        return ResponseEntity.ok(prestamoLogica.obtenerTodosLosPrestamos());
    }

    // Obtener un préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamo = prestamoLogica.obtenerPrestamoPorId(id);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un préstamo
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamoActualizado) {
        Prestamo actualizado = prestamoLogica.actualizarPrestamo(id, prestamoActualizado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un préstamo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        prestamoLogica.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener préstamos por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorUsuario(@PathVariable Integer idUsuario) {
        List<Prestamo> prestamos = prestamoLogica.obtenerPrestamosPorUsuario(idUsuario);
        return ResponseEntity.ok(prestamos);
    }
}