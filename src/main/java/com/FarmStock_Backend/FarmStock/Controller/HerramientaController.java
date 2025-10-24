package com.FarmStock_Backend.FarmStock.Controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Service.HerramientaLogica;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/herramienta")
public class HerramientaController {
    private final HerramientaLogica service;

    public HerramientaController(HerramientaLogica service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Herramientas> crearHerramienta(@Valid @RequestBody Herramientas herramienta){
        Herramientas nuevaHerramienta = service.crearHerramienta(herramienta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHerramienta);
    }

    @GetMapping("/hoy")
    public ResponseEntity<List<Herramientas>> obtenerHerramientasDeHoy() {
        List<Herramientas> herramientasHoy = service.obtenerHerramientasDeHoy();
        return ResponseEntity.ok(herramientasHoy);
    }

    @GetMapping
    public ResponseEntity<List<Herramientas>> obtenerTodasHerramientas() {
        List<Herramientas> herramientas = service.obtenerTodasHerramientas();
        return ResponseEntity.ok(herramientas);
    }
}
