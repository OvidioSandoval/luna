package com.luna.app.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.app.entidades.Producto;
import com.luna.app.service.ProductoService;
import java.util.List;

@RestController
@RequestMapping("/api/producto")

public class ProductoControl {

    @Autowired
    private ProductoService productoService;

    // Constructor de la clase ProductoControl

    @GetMapping("/lista")
    public List<Producto> obtenerProductos() {

        return productoService.obtenerProductos();
    }

    // Obtener un producto por su ID

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    // Crear un producto 

    @PostMapping("/crear/{producto}")
    public ResponseEntity<Producto> crearProducto(@RequestBody @Validated  Producto producto) {
        
        Producto nuevoProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // Actualizar un producto 

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody @Validated  Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return productoActualizado != null ? ResponseEntity.ok(productoActualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar un producto 
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}

