package com.FarmStock_Backend.FarmStock.Service;
import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Herramientas;
import com.FarmStock_Backend.FarmStock.Repository.HerramientasRepository;


@Service
public class HerramientaLogica {

    private final HerramientasRepository herramientasRepository;

    public HerramientaLogica(HerramientasRepository herramientasRepository){
        this.herramientasRepository = herramientasRepository;
    }

    public Herramientas crearHerramienta(Herramientas herramienta){
        return herramientasRepository.save(herramienta);
    }

}
