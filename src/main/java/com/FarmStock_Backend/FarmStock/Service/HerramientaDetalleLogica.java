package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;

@Service
public class HerramientaDetalleLogica {
    private final Herramienta_detalleRepository herramientaDetalleRepository;

    public HerramientaDetalleLogica(Herramienta_detalleRepository herramientaDetalleRepository) {
        this.herramientaDetalleRepository = herramientaDetalleRepository;
    }

    public Herramienta_detalle actualizarHerramientaDetalle(Integer id, Herramienta_detalle detalleActualizado) {
        Optional<Herramienta_detalle> detalleOptional = herramientaDetalleRepository.findById(id);

        if (detalleOptional.isPresent()) {
            Herramienta_detalle detalleExistente = detalleOptional.get();
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