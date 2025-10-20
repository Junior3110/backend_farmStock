package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;
import com.FarmStock_Backend.FarmStock.Repository.HerramientasRepository;

@Service
public class HerramientaDetalleLogica {
    private final HerramientasRepository herramientasRepository;
    private final Herramienta_detalleRepository herramientaDetalleRepository;

    public HerramientaDetalleLogica(HerramientasRepository herramientasRepository, Herramienta_detalleRepository herramientaDetalleRepository) {
        this.herramientasRepository = herramientasRepository;
        this.herramientaDetalleRepository = herramientaDetalleRepository;
    }

    public Herramienta_detalle actualizarHerramientaDetalle(Integer id, Herramienta_detalle detalleActualizado) {

        Optional<Herramienta_detalle> detalleOptional = herramientaDetalleRepository.findById(id);

        if (detalleOptional.isPresent()) {
            Herramienta_detalle detalleExistente = detalleOptional.get();

            // Actualizamos los campos permitidos
            detalleExistente.setHerramienta(detalleActualizado.getHerramienta());
            detalleExistente.setCodigoUnico(detalleActualizado.getCodigoUnico());
            detalleExistente.setEstado(detalleActualizado.getEstado());
            detalleExistente.setDisponible(detalleActualizado.getDisponible());
            detalleExistente.setFechaIngreso(detalleActualizado.getFechaIngreso());

            return herramientaDetalleRepository.save(detalleExistente);
        } else {
            throw new IllegalArgumentException("No se encontró el detalle de herramienta con id: " + id);
        }
    }

    public List<Herramienta_detalle> obtenerHerramientas(Integer id){
        return herramientaDetalleRepository.findByHerramienta_IdHerramienta(id);
    }
    
} 
