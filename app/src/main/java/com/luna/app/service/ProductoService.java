package com.luna.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.luna.app.entidades.Producto;
import com.luna.app.repositorio.ProductoRepositorio;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductoService {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> obtenerProductos() {
        return productoRepositorio.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepositorio.findById(id);
    }

    public Producto crearProducto(@RequestBody Producto producto) {
        List<Producto> productosEncontrados = productoRepositorio.findByDescripcion(producto.getDescripcion());
        // Validaciones antes de guardar
        if (!productosEncontrados.isEmpty()) {
            throw new IllegalArgumentException("El producto ya existe");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor que 0");
        }
        producto.setDescripcion(producto.getDescripcion());
        producto.setPrecio(producto.getPrecio());
        producto.setStock(producto.getStock());
        return productoRepositorio.save(producto);

    }

    public Producto actualizarProducto(Long id, @Validated Producto producto) {
        return productoRepositorio.findById(id).map(productoExistente -> {
            // Validaciones antes de actualizar
            if (producto.getPrecio() <= 0) {
                throw new IllegalArgumentException("El precio debe ser mayor a cero");
            }
            if (producto.getStock() < 0) {
                throw new IllegalArgumentException("El stock no puede ser menor que 0");
            }

            // Actualiza solo los valores necesarios
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setStock(producto.getStock());

            // Guarda y devuelve el producto actualizado
            return productoRepositorio.save(productoExistente);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto eliminarProducto(Long id) {
        Producto productoExistente = productoRepositorio.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No se encontró el producto con ID: " + id));

        // Validaciones antes de guardar
        if (!productoRepositorio.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: Producto no encontrado con ID: " + id);
        }
        productoRepositorio.deleteById(id);
        return productoExistente;
    }
}
