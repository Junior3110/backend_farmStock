package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Model.Prestamo;
import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Model.Aprendiz;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;
import com.FarmStock_Backend.FarmStock.Repository.PrestamoRepository;
import com.FarmStock_Backend.FarmStock.Repository.UsuarioRepository;
import com.FarmStock_Backend.FarmStock.Repository.AprendizRepository;

@Service
public class PrestamoLogica {

    private final PrestamoRepository prestamoRepository;
    private final Herramienta_detalleRepository herramientadetalleRepository;   
    private final UsuarioRepository usuarioRepository;
    private final AprendizRepository aprendizRepository; 

    @Autowired
    public PrestamoLogica(PrestamoRepository prestamoRepository, Herramienta_detalleRepository herramientadetalleRepository, UsuarioRepository usuarioRepository, AprendizRepository aprendizRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientadetalleRepository = herramientadetalleRepository;
        this.usuarioRepository = usuarioRepository;
        this.aprendizRepository = aprendizRepository; 
    }

    public Prestamo crearPrestamo(String codigo, Integer idUsuario, String numeroDocumento, Prestamo prestamo) {
        Herramienta_detalle herramientaDetalle = herramientadetalleRepository.findByCodigoUnico(codigo)
            .orElseThrow(() -> new RuntimeException("no se encontro ninguna herramienta con este codigo: " + codigo));
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("no se encontro usuario con este id: " + idUsuario));
        Herramientas herramienta = herramientaDetalle.getHerramienta();

        Aprendiz aprendiz = aprendizRepository.findByNumeroDocumento(numeroDocumento).orElseThrow(() -> new RuntimeException("No se encontro ningun Aprendiz con este Documento" + numeroDocumento));

        prestamo.setHerramientaDetalle(herramientaDetalle);
        prestamo.setHerramienta(herramienta);
        prestamo.setUsuario(usuario);
        prestamo.setAprendiz(aprendiz); 

        return prestamoRepository.save(prestamo);
    }

    // falta metodo de devolver la herrmaienta 

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
        return prestamoRepository.findByUsuario_IdUsuario(idUsuario);
    }
}