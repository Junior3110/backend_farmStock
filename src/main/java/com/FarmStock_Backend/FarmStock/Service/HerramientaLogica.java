package com.FarmStock_Backend.FarmStock.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;
import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Repository.Herramienta_detalleRepository;
import com.FarmStock_Backend.FarmStock.Repository.HerramientasRepository;

@Service
public class HerramientaLogica {

    private final HerramientasRepository herramientasRepository;
    private final Herramienta_detalleRepository herramientaDetalleRepository;

    public HerramientaLogica(HerramientasRepository herramientasRepository,
                             Herramienta_detalleRepository herramientaDetalleRepository) {
        this.herramientasRepository = herramientasRepository;
        this.herramientaDetalleRepository = herramientaDetalleRepository;
    }

    /**
     * Crea una herramienta y genera sus detalles (unidades físicas).
     * - Guarda la herramienta base.
     * - Crea N registros en detalle según 'cantidad' con código único:
     *   NOMBRE-MAYUS-ID_HERRAMIENTA-XXX y los marca como 'Disponible'.
     * - Genera QR y código de barras para cada detalle.
     */
    public Herramientas crearHerramienta(Herramientas herramienta) {
            Herramientas herramientaGuardada = herramientasRepository.save(herramienta);

            int cantidad = herramientaGuardada.getCantidad() != null ? herramientaGuardada.getCantidad() : 0;

            for (int i = 1; i <= cantidad; i++) {
                Herramienta_detalle detalle = new Herramienta_detalle();
                detalle.setHerramienta(herramientaGuardada);
                String codigo = herramientaGuardada.getNombre().toUpperCase() + "-" 
                + herramientaGuardada.getIdHerramienta() + "-" 
                + String.format("%03d", i);
                detalle.setCodigoUnico(codigo);
                detalle.setEstado("Disponible");
                detalle.setDisponible(true);
                detalle.setFechaIngreso(herramientaGuardada.getFechaRegistro());
                herramientaDetalleRepository.save(detalle);

                // 👇 Llamas los métodos desde HerramientaDetalleLogica
                HerramientaDetalleLogica.generarCodigoQR(codigo, "qr_" + codigo + ".png");
                HerramientaDetalleLogica.generarCodigoDeBarras(codigo, "bar_" + codigo + ".png");
            }

            return herramientaGuardada;
        }

    /**
     * Lista herramientas registradas en la fecha actual (hoy).
     */
    public List<Herramientas> obtenerHerramientasDeHoy() {
        LocalDate hoy = LocalDate.now();
        return herramientasRepository.findByFechaRegistro(hoy);
    }

    /**
     * Lista todas las herramientas existentes.
     */
    public List<Herramientas> obtenerTodasHerramientas() {
        return herramientasRepository.findAll();
    }

    /**
     * Obtiene una herramienta por su ID o lanza error si no existe.
     */
    public Herramientas obtenerPorId(Integer id) {
        Optional<Herramientas> opt = herramientasRepository.findById(id);
        return opt.orElseThrow(() -> new IllegalArgumentException("No se encontró herramienta con id: " + id));
    }

    /**
     * Actualiza datos de la herramienta y sincroniza la cantidad con sus detalles:
     * - Si aumenta la cantidad, crea nuevos detalles y códigos.
     * - Si disminuye, elimina detalles sobrantes (del final de la lista).
     */
    public Herramientas actualizarHerramienta(Integer id, Herramientas herramienta) {
        Optional<Herramientas> opt = herramientasRepository.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("No se encontró la herramienta con id: " + id);
        }
        Herramientas existente = opt.get();

        existente.setNombre(herramienta.getNombre());
        existente.setDescripcion(herramienta.getDescripcion());
        existente.setEstado(herramienta.getEstado());
        existente.setTipo(herramienta.getTipo());
        existente.setUbicacion(herramienta.getUbicacion());
        existente.setNumeroLote(herramienta.getNumeroLote());
        existente.setFechaRegistro(herramienta.getFechaRegistro());
        int oldCantidad = existente.getCantidad() != null ? existente.getCantidad() : 0;
        int newCantidad = herramienta.getCantidad() != null ? herramienta.getCantidad() : 0;
        existente.setCantidad(newCantidad);

        Herramientas guardada = herramientasRepository.save(existente);

        if (newCantidad > oldCantidad) {
            for (int i = oldCantidad + 1; i <= newCantidad; i++) {
                Herramienta_detalle detalle = new Herramienta_detalle();
                detalle.setHerramienta(guardada);
                String codigo = guardada.getNombre().toUpperCase() + "-" 
                   + guardada.getIdHerramienta() + "-" 
                   + String.format("%03d", i);
                detalle.setCodigoUnico(codigo);
                detalle.setEstado("Disponible");
                detalle.setDisponible(true);
                detalle.setFechaIngreso(guardada.getFechaRegistro());
                herramientaDetalleRepository.save(detalle);
            }
        } else if (newCantidad < oldCantidad) {
            List<Herramienta_detalle> detalles = herramientaDetalleRepository.findByHerramienta_IdHerramienta(guardada.getIdHerramienta());
            int toRemove = oldCantidad - newCantidad;
            for (int i = 0; i < toRemove && !detalles.isEmpty(); i++) {
                Herramienta_detalle d = detalles.get(detalles.size() - 1 - i);
                herramientaDetalleRepository.delete(d);
            }
        }

        return guardada;
    }

    /**
     * Elimina una herramienta y sus detalles asociados.
     * - Primero borra los detalles para evitar referencias huérfanas.
     */
    public void eliminarHerramienta(Integer id) {
        Optional<Herramientas> opt = herramientasRepository.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("No se encontró la herramienta con id: " + id);
        }
        List<Herramienta_detalle> detalles = herramientaDetalleRepository.findByHerramienta_IdHerramienta(id);
        if (detalles != null && !detalles.isEmpty()) {
            herramientaDetalleRepository.deleteAll(detalles);
        }
        herramientasRepository.deleteById(id);
    }
}