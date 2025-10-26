package com.FarmStock_Backend.FarmStock.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Service.HerramientaDetalleLogica;

@RestController
@RequestMapping("/api/herramienta-detalle")
@CrossOrigin(origins = "*")
public class Herramienta_DetalleController {
     private final HerramientaDetalleLogica herramientaDetalleLogica;

    public Herramienta_DetalleController(HerramientaDetalleLogica herramientaDetalleLogica) {
        this.herramientaDetalleLogica = herramientaDetalleLogica;
    }

    @GetMapping("/herramienta/{idHerramienta}")
    public List<Herramienta_detalle> obtenerPorHerramienta(@PathVariable Integer idHerramienta) {
        return herramientaDetalleLogica.obtenerHerramientas(idHerramienta);
    }

    @PutMapping("/{idDetalle}")
    public Herramienta_detalle actualizarDetalle(
            @PathVariable Integer idDetalle,
            @RequestBody Herramienta_detalle detalleActualizado) {
        return herramientaDetalleLogica.actualizarHerramientaDetalle(idDetalle, detalleActualizado);
    }
}