package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Prestamo;
import com.FarmStock_Backend.FarmStock.Repository.PrestamoRepository;

@Service
public class PrestamoLogica {

    private final PrestamoRepository prestamoRepository;

    @Autowired
    public PrestamoLogica(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    // Método para crear un nuevo préstamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    // Método para obtener todos los préstamos
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    // Método para obtener un préstamo por ID
    public Optional<Prestamo> obtenerPrestamoPorId(Integer idPrestamo) {
        return prestamoRepository.findById(idPrestamo);
    }

    // Método para actualizar un préstamo
    public Prestamo actualizarPrestamo(Integer idPrestamo, Prestamo prestamoActualizado) {
        if (!prestamoRepository.existsById(idPrestamo)) {
            throw new IllegalArgumentException("El préstamo con ID " + idPrestamo + " no existe.");
        }
        prestamoActualizado.setIdPrestamo(idPrestamo); // Asegúrate de que el ID se mantenga
        return prestamoRepository.save(prestamoActualizado);
    }

    // Método para eliminar un préstamo
    public void eliminarPrestamo(Integer idPrestamo) {
        if (!prestamoRepository.existsById(idPrestamo)) {
            throw new IllegalArgumentException("El préstamo con ID " + idPrestamo + " no existe.");
        }
        prestamoRepository.deleteById(idPrestamo);
    }

    // Método para obtener préstamos por usuario
    public List<Prestamo> obtenerPrestamosPorUsuario(Integer idUsuario) {
        return (List<Prestamo>) prestamoRepository.findByUsuario_IdUsuario(idUsuario);
    }
}
