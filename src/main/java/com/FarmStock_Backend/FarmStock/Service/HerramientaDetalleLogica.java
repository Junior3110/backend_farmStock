package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;
import com.FarmStock_Backend.FarmStock.Repository.HerramientasRepository;

@Service
public class HerramientaDetalleLogica {
    private final Herramienta_detalleRepository herramientaDetalleRepository;
    private final HerramientasRepository herramientasRepository;

    // Constructor con ambas dependencias
    public HerramientaDetalleLogica(Herramienta_detalleRepository herramientaDetalleRepository,
                                    HerramientasRepository herramientasRepository) {
        this.herramientaDetalleRepository = herramientaDetalleRepository;
        this.herramientasRepository = herramientasRepository;
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

    // Implementación de crearHerramienta que el test espera
    public Herramientas crearHerramienta(Herramientas herramienta) {
        // Guardar la herramienta principal
        Herramientas saved = herramientasRepository.save(herramienta);

        Integer cantidad = saved.getCantidad() != null ? saved.getCantidad() : 0;
        String nombreUpper = saved.getNombre() != null ? saved.getNombre().toUpperCase() : "HERRAMIENTA";
        Integer idHerr = saved.getIdHerramienta() != null ? saved.getIdHerramienta() : 0;

        for (int i = 1; i <= cantidad; i++) {
            Herramienta_detalle detalle = new Herramienta_detalle();
            detalle.setHerramienta(saved);
            detalle.setEstado("Disponible");
            detalle.setDisponible(true);
            detalle.setFechaIngreso(saved.getFechaRegistro());
            String codigo = String.format("%s-%d-%03d", nombreUpper, idHerr, i);
            detalle.setCodigoUnico(codigo);
            herramientaDetalleRepository.save(detalle);
        }

        return saved;
    }
}