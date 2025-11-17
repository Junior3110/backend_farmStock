package com.FarmStock_Backend.FarmStock.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.FarmStock_Backend.FarmStock.Model.Mantenimiento;
import com.FarmStock_Backend.FarmStock.Service.MantenimientoLogica;

/**
 * Controlador REST para gestionar mantenimientos y daños de herramientas.
 */
@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {

    private final MantenimientoLogica mantenimientoLogica;

    public MantenimientoController(MantenimientoLogica mantenimientoLogica) {
        this.mantenimientoLogica = mantenimientoLogica;
    }

    /**
     * POST /mantenimientos?idDetalle=X&idUsuario=Y
     * Registra un nuevo mantenimiento o daño para una unidad física específica.
     * Query params: idDetalle (requerido - unidad física), idUsuario (requerido)
     * Body: { "tipo": "MANTENIMIENTO" o "DAÑO", "descripcion": "...", "estado": "PENDIENTE" }
     */
    @PostMapping
    public ResponseEntity<?> registrarMantenimiento(
            @RequestParam Integer idDetalle,
            @RequestParam Integer idUsuario,
            @RequestBody Mantenimiento mantenimiento) {
        try {
            Mantenimiento creado = mantenimientoLogica.registrarMantenimiento(
                idDetalle, idUsuario, mantenimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * GET /mantenimientos
     * Lista todos los mantenimientos y daños registrados.
     */
    @GetMapping
    public ResponseEntity<List<Mantenimiento>> listarTodos() {
        return ResponseEntity.ok(mantenimientoLogica.obtenerTodos());
    }

    /**
     * GET /mantenimientos/{id}
     * Obtiene un mantenimiento/daño por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Mantenimiento mantenimiento = mantenimientoLogica.obtenerPorId(id);
            return ResponseEntity.ok(mantenimiento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * GET /mantenimientos/herramienta/{idHerramienta}
     * Obtiene todos los mantenimientos/daños de una herramienta específica.
     */
    @GetMapping("/herramienta/{idHerramienta}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorHerramienta(@PathVariable Integer idHerramienta) {
        return ResponseEntity.ok(mantenimientoLogica.obtenerPorHerramienta(idHerramienta));
    }

    /**
     * GET /mantenimientos/tipo/{tipo}
     * Obtiene todos los registros de un tipo específico (MANTENIMIENTO o DAÑO).
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(mantenimientoLogica.obtenerPorTipo(tipo));
    }

    /**
     * GET /mantenimientos/usuario/{idUsuario}
     * Obtiene todos los mantenimientos/daños realizados por un usuario.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(mantenimientoLogica.obtenerPorUsuario(idUsuario));
    }

    /**
     * PUT /mantenimientos/{id}
     * Actualiza un mantenimiento/daño existente (solo campos editables).
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMantenimiento(
            @PathVariable Integer id,
            @RequestBody Mantenimiento mantenimiento) {
        try {
            Mantenimiento actualizado = mantenimientoLogica.actualizarMantenimiento(id, mantenimiento);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * DELETE /mantenimientos/{id}
     * Elimina un mantenimiento/daño del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMantenimiento(@PathVariable Integer id) {
        try {
            mantenimientoLogica.eliminarMantenimiento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
