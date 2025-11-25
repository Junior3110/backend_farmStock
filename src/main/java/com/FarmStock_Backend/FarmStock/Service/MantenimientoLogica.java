package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Model.Mantenimiento;
import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;
import com.FarmStock_Backend.FarmStock.Repository.HerramientasRepository;
import com.FarmStock_Backend.FarmStock.Repository.MantenimientoRepository;
import com.FarmStock_Backend.FarmStock.Repository.UsuarioRepository;

@Service
public class MantenimientoLogica {

    private final MantenimientoRepository mantenimientoRepository;
    private final HerramientasRepository herramientasRepository;
    private final Herramienta_detalleRepository herramientaDetalleRepository;
    private final UsuarioRepository usuarioRepository;

    public MantenimientoLogica(MantenimientoRepository mantenimientoRepository,
                               HerramientasRepository herramientasRepository,
                               Herramienta_detalleRepository herramientaDetalleRepository,
                               UsuarioRepository usuarioRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.herramientasRepository = herramientasRepository;
        this.herramientaDetalleRepository = herramientaDetalleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra un nuevo mantenimiento o daño para una unidad física específica.
     * Busca por código único de herramienta y número de documento del usuario.
     * Cambia automáticamente el estado de la herramienta_detalle a "Mantenimiento" y disponible=false.
     */
    public Mantenimiento registrarMantenimiento(String codigoUnico, String numeroDocumento, 
                                                 Mantenimiento mantenimiento) {
        // Buscar la unidad física específica por código único
        Herramienta_detalle detalle = herramientaDetalleRepository.findByCodigoUnico(codigoUnico)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró la herramienta con código: " + codigoUnico));

        // Obtener la herramienta general desde el detalle
        Herramientas herramienta = detalle.getHerramienta();
        
        // Buscar usuario por número de documento
        Usuario usuario = usuarioRepository.findByNumeroDocumento(numeroDocumento)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con documento: " + numeroDocumento));

        mantenimiento.setHerramienta(herramienta);
        mantenimiento.setHerramientaDetalle(detalle);
        mantenimiento.setUsuario(usuario);
        
        // Si no se proporciona fecha, usar la fecha actual
        if (mantenimiento.getFechaMantenimiento() == null) {
            mantenimiento.setFechaMantenimiento(java.time.LocalDate.now());
        }

        // Cambiar estado de la herramienta_detalle a Mantenimiento
        detalle.setEstado("Mantenimiento");
        detalle.setDisponible(false);
        herramientaDetalleRepository.save(detalle);

        return mantenimientoRepository.save(mantenimiento);
    }

    /**
     * Obtiene todos los mantenimientos y daños registrados.
     */
    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoRepository.findAll();
    }

    /**
     * Obtiene todos los mantenimientos activos (no completados).
     * Filtra por estado diferente de COMPLETADO.
     */
    public List<Mantenimiento> obtenerMantenimientosActivos() {
        return mantenimientoRepository.findAll().stream()
                .filter(m -> !"COMPLETADO".equals(m.getEstado()))
                .toList();
    }

    /**
     * Obtiene un mantenimiento/daño por ID.
     */
    public Mantenimiento obtenerPorId(Integer id) {
        return mantenimientoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el registro con id: " + id));
    }

    /**
     * Obtiene todos los registros (mantenimientos/daños) de una herramienta.
     */
    public List<Mantenimiento> obtenerPorHerramienta(Integer idHerramienta) {
        return mantenimientoRepository.findByHerramienta_IdHerramienta(idHerramienta);
    }

    /**
     * Obtiene todos los registros de un tipo específico (MANTENIMIENTO o DAÑO).
     */
    public List<Mantenimiento> obtenerPorTipo(String tipo) {
        return mantenimientoRepository.findByTipo(tipo);
    }

    /**
     * Obtiene todos los registros realizados por un usuario específico.
     */
    public List<Mantenimiento> obtenerPorUsuario(Integer idUsuario) {
        return mantenimientoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    /**
     * Cuenta cuántos mantenimientos tiene una herramienta específica.
     */
    public Long contarMantenimientosPorHerramienta(Integer idHerramienta) {
        return mantenimientoRepository.contarPorHerramientaYTipo(idHerramienta, "MANTENIMIENTO");
    }

    /**
     * Cuenta cuántos daños tiene una herramienta específica.
     */
    public Long contarDañosPorHerramienta(Integer idHerramienta) {
        return mantenimientoRepository.contarPorHerramientaYTipo(idHerramienta, "DAÑO");
    }

    /**
     * Actualiza un registro de mantenimiento/daño existente.
     * Solo actualiza campos editables: tipo, descripcion, estado.
     * Si el estado cambia a COMPLETADO, vuelve la herramienta a Disponible.
     */
    public Mantenimiento actualizarMantenimiento(Integer id, Mantenimiento mantenimiento) {
        Mantenimiento existente = mantenimientoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el registro con id: " + id));

        // Solo actualizar campos que el usuario puede modificar
        if (mantenimiento.getTipo() != null) {
            existente.setTipo(mantenimiento.getTipo());
        }
        if (mantenimiento.getDescripcion() != null) {
            existente.setDescripcion(mantenimiento.getDescripcion());
        }
        if (mantenimiento.getEstado() != null) {
            existente.setEstado(mantenimiento.getEstado());
            
            // Si el mantenimiento se completó, volver la herramienta a disponible
            if ("COMPLETADO".equals(mantenimiento.getEstado())) {
                Herramienta_detalle detalle = existente.getHerramientaDetalle();
                if (detalle != null) {
                    detalle.setEstado("Disponible");
                    detalle.setDisponible(true);
                    herramientaDetalleRepository.save(detalle);
                }
            }
        }

        return mantenimientoRepository.save(existente);
    }

    /**
     * Elimina un registro de mantenimiento/daño.
     */
    public void eliminarMantenimiento(Integer id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el registro con id: " + id);
        }
        mantenimientoRepository.deleteById(id);
    }
}