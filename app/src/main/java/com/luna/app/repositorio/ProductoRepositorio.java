package com.luna.app.repositorio;

import com.luna.app.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
    
    List<Producto> findByDescripcion(String descripcion);
    
}