package com.luna.app.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.luna.app.entidades.Ventas;


public interface VentaRepositorio extends JpaRepository<Ventas, Long> {
    List<Ventas> findByFecha(LocalDateTime fecha);
}
