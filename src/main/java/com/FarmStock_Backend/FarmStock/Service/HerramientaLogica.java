package com.FarmStock_Backend.FarmStock.Service;

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

    public Herramientas crearHerramienta(Herramientas herramienta) {
        // 1️⃣ Guardamos la herramienta general
        Herramientas herramientaGuardada = herramientasRepository.save(herramienta);

        // 2️⃣ Generamos los detalles individuales (por ejemplo, 10 palas)
        int cantidad = herramientaGuardada.getCantidad();

        for (int i = 1; i <= cantidad; i++) {
            Herramienta_detalle detalle = new Herramienta_detalle();

            detalle.setHerramienta(herramientaGuardada);

            String codigo = herramientaGuardada.getNombre().toUpperCase() + "-" 
               + herramientaGuardada.getIdHerramienta() + "-" 
               + String.format("%03d", i);
            detalle.setCodigoUnico(codigo);

            detalle.setEstado("Disponible");

            detalle.setDisponible(true);
            detalle.setFechaIngreso(herramientaGuardada.getFecha_registro());

            herramientaDetalleRepository.save(detalle);
        }

        return herramientaGuardada;
    }
}
