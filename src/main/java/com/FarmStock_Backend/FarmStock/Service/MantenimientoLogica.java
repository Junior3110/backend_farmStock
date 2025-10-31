package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Mantenimiento;
import com.FarmStock_Backend.FarmStock.Repository.MantenimientoRepository;

@Service
public class MantenimientoLogica {

    private final MantenimientoRepository mantenimientoRepository;

    public MantenimientoLogica(MantenimientoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    public Mantenimiento crearMantenimiento(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    public List<Mantenimiento> verMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento buscarMantenimiento(Integer id) {
        return mantenimientoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el mantenimiento con id: " + id));
    }

    public Mantenimiento actualizarMantenimiento(Integer id, Mantenimiento mantenimiento) {
        Optional<Mantenimiento> opt = mantenimientoRepository.findById(id);
        if (opt.isPresent()) {
            Mantenimiento actual = opt.get();
            actual.setIdHerramienta(mantenimiento.getIdHerramienta());
            actual.setDescripcion(mantenimiento.getDescripcion());
            actual.setFechaMantenimiento(mantenimiento.getFechaMantenimiento());
            actual.setRealizadoPor(mantenimiento.getRealizadoPor());
            actual.setIdDetalle(mantenimiento.getIdDetalle());
            return mantenimientoRepository.save(actual);
        } else {
            throw new IllegalArgumentException("No se encontró el mantenimiento con id: " + id);
        }
    }

    public void eliminarMantenimiento(Integer id) {
        if (mantenimientoRepository.existsById(id)) {
            mantenimientoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el mantenimiento con id: " + id);
        }
    }
}