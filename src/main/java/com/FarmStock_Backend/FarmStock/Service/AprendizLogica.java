package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Aprendiz;
import com.FarmStock_Backend.FarmStock.Repository.AprendizRepository;

@Service
public class AprendizLogica {

    private final AprendizRepository aprendizRepository;

    public AprendizLogica(AprendizRepository aprendizRepository) {
        this.aprendizRepository = aprendizRepository;
    }

    
    public Aprendiz crearAprendiz(Aprendiz aprendiz) {
        // Verificar si ya existe un aprendiz con ese número de documento
        Optional<Aprendiz> existente = aprendizRepository.findByNumeroDocumento(aprendiz.getNumeroDocumento());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un aprendiz con el número de documento: " + aprendiz.getNumeroDocumento());
        }
        return aprendizRepository.save(aprendiz);
    }

    
    public List<Aprendiz> verAprendices() {
        return aprendizRepository.findAll();
    }

    
    public Aprendiz buscarAprendiz(Integer idAprendiz) {
        return aprendizRepository.findById(idAprendiz)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el aprendiz con id: " + idAprendiz));
    }


    public Aprendiz buscarPorDocumento(String tipoDocumento, String numeroDocumento) {
        return aprendizRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
            .orElseThrow(() -> new IllegalArgumentException(
                "No se encontró un aprendiz con documento: " + tipoDocumento + " " + numeroDocumento
            ));
    }

    
    public Aprendiz actualizarAprendiz(Integer idAprendiz, Aprendiz aprendiz) {
        Optional<Aprendiz> opt = aprendizRepository.findById(idAprendiz);
        if (opt.isPresent()) {
            Aprendiz actual = opt.get();
            actual.setNombre(aprendiz.getNombre());
            actual.setTipoDocumento(aprendiz.getTipoDocumento());
            actual.setNumeroDocumento(aprendiz.getNumeroDocumento());
            actual.setNumeroFicha(aprendiz.getNumeroFicha());
            return aprendizRepository.save(actual);
        } else {
            throw new IllegalArgumentException("No se encontró el aprendiz con id: " + idAprendiz);
        }
    }

    
    public void eliminarAprendiz(Integer idAprendiz) {
        if (aprendizRepository.existsById(idAprendiz)) {
            aprendizRepository.deleteById(idAprendiz);
        } else {
            throw new IllegalArgumentException("No se encontró el aprendiz con id: " + idAprendiz);
        }
    }
}
