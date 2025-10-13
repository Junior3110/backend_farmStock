package com.FarmStock_Backend.FarmStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FarmStock_Backend.FarmStock.Model.Herramienta_detalle;

@Repository
public interface Herramienta_detalleRepository extends JpaRepository<Herramienta_detalle, Integer> {
}
