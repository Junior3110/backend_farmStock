package com.FarmStock_Backend.FarmStock.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmStock_Backend.FarmStock.Model.Herramientas;

public interface  HerramientasRepository extends JpaRepository<Herramientas, Integer> {
    Optional<Herramientas> findByNombre(String nombre);
}
