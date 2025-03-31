package com.luna.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luna.app.entidades.Producto;
import com.luna.app.entidades.Ventas;
import com.luna.app.repositorio.ProductoRepositorio;
import com.luna.app.repositorio.VentaRepositorio;

@Service

public class VentaService {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Transactional
    public Ventas registrarVenta(Long producto_id, Integer cantidad) {
        Producto productos = productoRepositorio.findById(producto_id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar que haya stock suficiente
        if (productos.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        // Descontar el stock
        productos.setStock(productos.getStock() - cantidad);
        productoRepositorio.save(productos);
        // Crear la venta
        Ventas venta = new Ventas();
        venta.setProductos(productos);
        venta.setCantidad(cantidad);
        venta.setFecha(LocalDateTime.now()); 

        // Guardar la venta y retornarla
        return ventaRepositorio.save(venta);
    }

    public List<Ventas> listarVentas() {
        return ventaRepositorio.findAll();
    }

    public Optional<Ventas> obtenerPorId(Long id) {
        return ventaRepositorio.findById(id);
    }

}
