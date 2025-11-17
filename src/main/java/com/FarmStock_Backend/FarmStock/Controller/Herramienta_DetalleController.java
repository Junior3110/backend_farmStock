package com.FarmStock_Backend.FarmStock.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Service.HerramientaDetalleLogica;
//lka haga drop datanbase
@RestController
@RequestMapping("/api/herramienta-detalle")
@CrossOrigin(origins = "*")
public class Herramienta_DetalleController {
     private final HerramientaDetalleLogica herramientaDetalleLogica;

    public Herramienta_DetalleController(HerramientaDetalleLogica herramientaDetalleLogica) {
        this.herramientaDetalleLogica = herramientaDetalleLogica;
    }

    /**
     * GET /api/herramienta-detalle
     * Obtiene todos los detalles de herramientas
     */
    @GetMapping
    public List<Herramienta_detalle> obtenerTodosLosDetalles() {
        return herramientaDetalleLogica.obtenerTodos();
    }

    /**
     * GET /api/herramienta-detalle/herramienta/{idHerramienta}
     * Obtiene todos los detalles de una herramienta específica
     */
    @GetMapping("/herramienta/{idHerramienta}")
    public List<Herramienta_detalle> obtenerPorHerramienta(@PathVariable Integer idHerramienta) {
        return herramientaDetalleLogica.obtenerHerramientas(idHerramienta);
    }

    /**
     * GET /api/herramienta-detalle/codigo/{codigoUnico}
     * Obtiene un detalle específico por código único
     */
    @GetMapping("/codigo/{codigoUnico}")
    public ResponseEntity<?> obtenerPorCodigoUnico(@PathVariable String codigoUnico) {
        try {
            Herramienta_detalle detalle = herramientaDetalleLogica.obtenerPorCodigoUnico(codigoUnico);
            return ResponseEntity.ok(detalle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idDetalle}")
    public Herramienta_detalle actualizarDetalle(
            @PathVariable Integer idDetalle,
            @RequestBody Herramienta_detalle detalleActualizado) {
        return herramientaDetalleLogica.actualizarHerramientaDetalle(idDetalle, detalleActualizado);
    }
}