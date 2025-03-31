package com.luna.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luna.app.entidades.VentaDto;
import com.luna.app.entidades.Ventas;
import com.luna.app.service.VentaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/venta")
public class VentaControl {

    @Autowired
    private VentaService ventaService;

    // Endpoint para registrar una venta
    @PostMapping("/registrar")
    public ResponseEntity<Ventas> registrarVenta(@Valid @RequestBody VentaDto ventaDto) {
        Ventas venta = ventaService.registrarVenta(ventaDto.getProducto_id(), ventaDto.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).body(venta);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Ventas>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> obtenerPorId(@PathVariable Long id) {
        return ventaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

}
}