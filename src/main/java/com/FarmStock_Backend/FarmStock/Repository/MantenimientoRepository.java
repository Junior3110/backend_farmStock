package com.FarmStock_Backend.FarmStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmStock_Backend.FarmStock.Model.Mantenimiento;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
    // puedes añadir consultas personalizadas si las necesitas
}