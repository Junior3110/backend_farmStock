package com.FarmStock_Backend.FarmStock.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;

@Repository
public interface Herramienta_detalleRepository extends JpaRepository<Herramienta_detalle, Integer> {
    List<Herramienta_detalle> findByHerramienta_IdHerramienta(Integer idHerramienta);
}