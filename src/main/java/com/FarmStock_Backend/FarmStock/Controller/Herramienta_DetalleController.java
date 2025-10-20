package com.FarmStock_Backend.FarmStock.Controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Service.HerramientaDetalleLogica;

@RestController
@RequestMapping("/api/herramienta-detalle") // 👈 Ruta base
@CrossOrigin(origins = "*") // 👈 Permite probar desde Postman o frontend sin problemas de CORS
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
